package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            if(isAmountValid(startingBalance)){
                this.email = email;
                this.balance = startingBalance;
            }
            else throw new IllegalArgumentException("Starting balance must be positive and have no more than two decimal places");
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if(amount < 0){}
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        // Emails must contain an "@" and at least 1 '.'
        if (email.indexOf('@') == -1 || email.indexOf('.') == -1){
            return false;
        }
        else {

            // Next check for illegal characters
            String LEGAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.-_@";
            String SPECIAL_CHARS = ".-_@";
            for(int i = 0; i < email.length() - 1; i++){
                if(LEGAL_CHARS.contains(email.substring(i, i+1)) == false) {
                    System.out.println(email.substring(i,i+1));
                    return false;
                }

                // Check if there are two special-characters next to each other
                if(SPECIAL_CHARS.contains(email.substring(i, i+1))){
                    if(SPECIAL_CHARS.contains(email.substring(i+1, i+2))){
                        return false;
                    }
                }
            }

            // Splits email into prefix and domain, we will check for a valid name first
            String[] elements = email.split("@");

            // This line ensures there is only 1 "@" present.
            if(elements.length != 2){
                return false;
            }

            // Some helpful variable names
            String prefix = elements[0];
            String domain = elements[1];

            // Checks if either prefix or domain are empty
            if(prefix.length() < 1 || domain.length() < 1){
                return false;
            }

            // Check if prefix or domain begin or end with special char
            else if(SPECIAL_CHARS.contains(prefix.substring(0, 1))
                 || SPECIAL_CHARS.contains(prefix.substring(prefix.length()-1, prefix.length()))
                 || SPECIAL_CHARS.contains(domain.substring(0, 1))
                 ||  SPECIAL_CHARS.contains(domain.substring(domain.length() - 1, domain.length())))
                {
                    return false;
                }

            else{
                // These characters were acceptable in the prefix, but not the domain
                if(domain.contains("_")){
                    return false;
                }

                // Finally look at the elements of the domain
                String[] domainElements = domain.split("\\.");

                // Checks that only one '.' is present in domain
                if(domainElements.length != 2){
                    return false;
                }

                String domainName = domainElements[0];
                String topLevelDomain = domainElements[1];

                // Minimum requirement of length needs to be met
                if(topLevelDomain.length() < 2){
                    return false;
                }

                else{
                    return true;
                }
            }
        }
    }
    
    
    /**
     * @return true if amount is positive (amount > 0) and has two or fewer decimal places, otherwise false
     */
    public static boolean isAmountValid(double amount){
        if(amount <= 0){
            return false;
        }

        // Check to see if there are two or less decimal places.
        String num = Double.toString(amount);
        int integers = num.indexOf('.');
        int decimals = num.length() - integers - 1;
        if(decimals > 2){
            return false;
        }

        return true;
    }

    /**
     * @param amount amount to be deposited
     * @throws IllegalArgumentException if amount is negative or has more than 2 decimal places.
     * @post increases balance by amount
     */
    public void deposit(double amount) throws IllegalArgumentException {
        if(isAmountValid(amount)){
            balance += amount;
        }
        else{
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    /**
     * @param amount to be transferred
     * @throws IllegalArgumentException if amount is negative or has more than 2 decimal places
     * @throws InsufficientFundsException if balance is too low to transfer
     * @post amount is withdrawn from source and deposited in target account
     */
    public void transfer(BankAccount target, double amount) throws IllegalArgumentException, InsufficientFundsException {
        if(isAmountValid(amount) == false){
            throw new IllegalArgumentException("Invalid amount");
        }
        else{
            // Pull money from first count
            try{
                this.withdraw(amount);
            }
            catch(Exception e){
                throw new InsufficientFundsException("Insufficient funds");
            }

            // Put money into the second account
            target.deposit(amount);
        }
    }
}
