package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        //Initial Test
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        //High balance
        BankAccount bankAccount2 = new BankAccount("a@b.com", 2000);
        assertEquals(2000, bankAccount2.getBalance());

        //Low balance
        BankAccount bankAccount3 = new BankAccount("a@b.com", 2);
        assertEquals(2, bankAccount3.getBalance());

        /* Balance cannot be negative
        //Negative balance
        BankAccount bankAccount4 = new BankAccount("a@b.com", -20);
        assertEquals(-20, bankAccount4.getBalance()); */

    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        //first initial tests
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //Metod should throw an exception for when the withdrawal is greater than account balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        //Test for an empty withdrawal
        BankAccount bankAccount2 = new BankAccount("a@b.com", 300);
        bankAccount2.withdraw(0);
        assertEquals(300, bankAccount2.getBalance());
        
        //Negative Numbered withdrawal 
        bankAccount2.withdraw(-10);
        assertEquals(300, bankAccount2.getBalance());

        

    }
        

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

        assertTrue(BankAccount.isEmailValid("a@gmail.com"));
        assertFalse(BankAccount.isEmailValid("a@gmail"));

        assertFalse(BankAccount.isEmailValid("@ab."));
        assertFalse(BankAccount.isEmailValid(".@ab"));


        assertFalse(BankAccount.isEmailValid("gmail"));
        assertFalse(BankAccount.isEmailValid("@a"));
        assertFalse(BankAccount.isEmailValid(".a"));

        
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}