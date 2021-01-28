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
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


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
}
