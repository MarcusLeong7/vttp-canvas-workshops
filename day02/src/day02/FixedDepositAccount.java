package day02;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class FixedDepositAccount {

    //Private Members
    private final String accountHolderName;
    private final String accountNumber;
    private float accountBalance;
    private float interest = 3;
    private int duration = 6;
    private List<String> transactions;
    private boolean close;
    private Date accountCreationDate;
    private Date accountClosingDate;
    private boolean interestRateChanged;
    private boolean durationChanged;


    Date date = new Date();
    
    Random rand = new SecureRandom();
    String bankNumber = String.format("%010d",rand.nextInt(1000000));

    // Constructor methods to initialize the account
    public FixedDepositAccount(String name , Float balance) {
        this.accountHolderName = name;
        this.accountNumber = bankNumber;
        this.accountBalance = balance;
        this.interest = 3;
        this.duration = 6;
        this.transactions = new ArrayList<>();
        this.close = false;
        this.interestRateChanged = false;
        this.durationChanged = false;
        

        addTransaction("Account created with balance:$" + balance);
    }
    public FixedDepositAccount(String name , Float balance, Float interest) {
        this.accountHolderName = name;
        this.accountNumber = bankNumber;
        this.accountBalance = balance;
        this.interest = interest;
        this.duration = 6;
        this.transactions = new ArrayList<>();
        this.close = false;
        if(this.interest!= 3) this.interestRateChanged = true;
        this.durationChanged = false;

        addTransaction("Account created with balance:$" + balance);
    }

    public FixedDepositAccount(String name , Float balance, Float interest, Integer duration) {
        this.accountHolderName = name;
        this.accountNumber = bankNumber;
        this.accountBalance = balance;
        this.interest = interest;
        this.duration = duration;
        this.transactions = new ArrayList<>();
        this.close = false;
        if(this.interest!= 3) this.interestRateChanged = true;
        if (this.duration != 6) this.durationChanged = true;
      

        addTransaction("Account created with balance:$" + balance);
    }

    // Private method to add a transaction to array list
    private void addTransaction(String transactionDetail) {
        transactions.add(transactionDetail);
    }
    
    // Getter Methods
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public float getAccountBalance() {
        return accountBalance;
    }
    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }
    public float getInterest() {
        return interest;
    }
    public void setInterest(float interest) {
        this.interest = interest;
    }
    public int getDuration() {
        return duration;
    }

    // Setter Methods
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public boolean isInterestRateChanged() {
        return interestRateChanged;
    }
    public void setInterestRateChanged(boolean interestRateChanged) {
        this.interestRateChanged = interestRateChanged;
    }
    public boolean isDurationChanged() {
        return durationChanged;
    }
    public void setDurationChanged(boolean durationChanged) {
        this.durationChanged = durationChanged;
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
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Random getRand() {
        return rand;
    }
    public void setRand(Random rand) {
        this.rand = rand;
    }
    public String getBankNumber() {
        return bankNumber;
    }
    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    // Deposit Method
    public void deposit(float amount) {
        if(!close) {
            System.out.printf("Unable to deposit money.Account Number %s is a fixed deposit account",accountNumber);
        } else {
            System.out.printf("Unable to perform transaction. Account Number %s has been closed.",accountNumber);
        }

    }

    // Withdraw Method
    public void withdraw(float amount) {
        if(!close) {
            System.out.printf("Unable to deposit money.Account Number %s is a fixed deposit account",accountNumber);
        } else {
            System.out.printf("Unable to perform transaction. Account Number %s has been closed.",accountNumber);
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

    public float getBalance() {
        Float balance = this.accountBalance += Math.pow(1 + interest,duration);
        return balance;
    }

}
