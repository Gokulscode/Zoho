package com.gokul.Transaction;

public class Transaction {
    private int transaction_id;
    private String type;
    private double amount;
    private double balance;

    public Transaction(int transaction_id, String type, double amount, double balance) {
        this.transaction_id = transaction_id;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return transaction_id+"\t"
                +type+"\t"
                +amount+"\t"
                +balance ;
    }
}
