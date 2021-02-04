package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
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
    
    /**
     * @post returns true is email is valid
     */
    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        else {
            String[] elements = email.split("@");
            if(elements.length != 2){
                return false;
            }
            else if(elements[0].length() < 1 || elements[1].length() < 1){
                return false;
            }
            else if(elements[1].indexOf(".") == -1){
                return false;
            }
            else{
                String domain = elements[1];
                String[] strings = domain.split("\\.");
                if(strings.length != 2){
                    return false;
                }
                else{
                    return true;
                }
            }
        }
    }
    
    /**
     * @throws IllegalArgumentException if amount is invalid (non-decimal or negative)
     */
    public static boolean isAmountValid(double amount){
        return false;
    }

     /**
     * @post increases the balance by amount if amount is non-negative and smaller than balance
    public void deposit (double amount){}

     /**
     * @post reduces the balance of accountFrom by amount and increases the balance of accountTo by amount if amount is non-negative and smaller than balance\
     * @throws IllegalArgumentException if amount is greater than accountTo
     */
    public void transfer(double amount, BankAccount accountTo, BankAccount accountFrom){}

    //BankAccount.transfer(acc1, acc2, 300)
    //acc1.transfer(acc2, 300)
    //compare spot in memory 'this' with passed in bank account

}

