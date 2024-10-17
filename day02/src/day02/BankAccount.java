package day02;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BankAccount {

    //Private Members
    private final String accountHolderName;
    private final String accountNumber;
    private float accountBalance;
    private List<String> transactions;
    private boolean close;
    private final Date accountCreationDate;
    private Date accountClosingDate;

    Date date = new Date();

    Random rand = new SecureRandom();
    String bankNumber = String.format("%010d",rand.nextInt(1000000));
    

    // Constructor method to initialize the account
    public BankAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = bankNumber;
        this.accountBalance = 0;
        this.transactions = new ArrayList<>();
        this.close = false;
        this.accountCreationDate = new Date();
        this.accountClosingDate = null;
    }

    public BankAccount(String accountHolderName, float initialAccountBalance) {
        this.accountHolderName = accountHolderName;
        this.accountBalance = initialAccountBalance;
        this.accountNumber = bankNumber;
        this.transactions = new ArrayList<>();
        this.close = false;
        this.accountCreationDate = new Date();
        this.accountClosingDate = null;

        addTransaction("Account created with initial account balance:$" + initialAccountBalance);

    }
    
    // Private method to add a transaction to array list
    private void addTransaction(String transactionDetail) {
        transactions.add(transactionDetail);
    }


    // Public getter methods (no setter for accountHolderName and accountNumber)
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    // Public setter methods
    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    public Date getAccountClosingDate() {
        return accountClosingDate;
    }

    public void setAccountClosingDate(Date accountClosingDate) {
        this.accountClosingDate = accountClosingDate;
    }

    // Deposit Method
    public void deposit(float amount) {
        if(close) {
            System.out.printf("Unable to deposit money.Account Number %s is closed",accountNumber);
            return;
        } else {
            accountBalance += amount;
            addTransaction("Deposited:$" + amount);
            System.out.printf("Date transacted %s", date);
        }

    }

    // Withdraw Method
    public void withdraw(float amount) {
        if(close) {
            System.out.printf("Unable to withdraw money.Account Number %s is closed", accountNumber);
            return;
        } 
        if ( amount > accountBalance) {
            System.out.printf("Insufficient balance to withdraw.Account Number %s has balance:$%.2f.%n",accountNumber,accountBalance);
            return;
        } else {
            accountBalance -= amount;
            addTransaction("Withdrew:$" + amount);
            System.out.printf("Date transacted %s", date);
        }
    }
    
    // Account Closing Date
    public void CloseAccount() {
        if (!close) {
            close = true;
            accountClosingDate = new Date();
            addTransaction("Account closed on:" + accountClosingDate);
            System.out.printf("Account Number %s is close on %s:",accountNumber, accountClosingDate);
        } else {
            System.out.printf("Account Number %s has already been closed", accountNumber);
        }

    }

}
