package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        //Initial Test
        BankAccount bankAccount = new BankAccount("a@b.com", 200.00);
        assertEquals(200, bankAccount.getBalance());

        //High balance
        BankAccount bankAccount2 = new BankAccount("a@b.com", 2000.00);
        assertEquals(2000, bankAccount2.getBalance());

        //Low balance
        BankAccount bankAccount3 = new BankAccount("a@b.com", 2.00);
        assertEquals(2, bankAccount3.getBalance());

        /* Balance cannot be negative
        //Negative balance
<<<<<<< HEAD
        BankAccount bankAccount4 = new BankAccount("a@b.com", -20.00);
        assertEquals(-20, bankAccount4.getBalance());
=======
        BankAccount bankAccount4 = new BankAccount("a@b.com", -20);
        assertEquals(-20, bankAccount4.getBalance()); */
>>>>>>> c95cf8cf02b0c607564f868dc681d585654f67be

    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        //Test for positive numerberd withdrawl 
        BankAccount bankAccount = new BankAccount("a@b.com", 200.00);
        bankAccount.withdraw(100.00);
        assertEquals(100, bankAccount.getBalance());

        //Method should throw an exception for amount is not a decimal 
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(100));

        //Method should throw an exception for when the withdrawal is greater than account balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300.00));

        //Test for a withdrawl after an exception
        bankAccount.withdraw(100.00);
        assertEquals(0, bankAccount.getBalance());

        //Test for an empty withdrawal
        BankAccount bankAccount2 = new BankAccount("a@b.com", 300.00);
        bankAccount2.withdraw(0.00);
        assertEquals(300.00, bankAccount2.getBalance());
        
        //Test for negative Numbered withdrawal 
        bankAccount2.withdraw(-10.00);
        assertEquals(300, bankAccount2.getBalance());

        

    }
        

    @Test
    void isEmailValidTest(){
        //Tests for a string vs. empty string
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

        //Tests for domain name
        assertTrue(BankAccount.isEmailValid("a@gmail.com"));
        assertFalse(BankAccount.isEmailValid("a@gmail"));

        //Tests for the placement of '.' and '@'
        assertFalse(BankAccount.isEmailValid("@ab."));
        assertFalse(BankAccount.isEmailValid(".@ab"));

        //Tests for the absence of '.' and '@'
        assertFalse(BankAccount.isEmailValid("gmail"));
        assertFalse(BankAccount.isEmailValid("@a"));
        assertFalse(BankAccount.isEmailValid(".a"));

<<<<<<< HEAD
        //Tests for the use and placement of characters
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));


        
    }

    @Test
    void isAmountValidTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 200.00);
=======
>>>>>>> c95cf8cf02b0c607564f868dc681d585654f67be
        
        //Tests for double amount, correct format
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

        //Test for negative number amount

        //Test for 


    }

    @Test

    void depositTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 200.00);

        //
        BankAccount bankAccount = new BankAccount("a@b.com", 200.00);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

    }

    void transferTest(){
        BankAccount bankAccount1 = new BankAccount("a@b.com", 500.00);
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200.00);

        //Test for a high number transfer
        bankAccount1.transfer(100,bankAccount1,bankAccount2);
        assertEquals(400.00, bankAccount1.getBalance());
        assertEquals(100.00, bankAccount2.getBalance());

        //Test for a low number transfer
        bankAccount1.transfer(10,bankAccount1,bankAccount2);
        assertEquals(100.00, bankAccount1.getBalance());
        assertEquals(100.00, bankAccount2.getBalance());

        //Test for a transfer where account is greater than accountTo's balance

        //Test for a negative number transer 

        //Test for a zero transfer

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200.00);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200.00, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100.00));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100));
    }


    

}