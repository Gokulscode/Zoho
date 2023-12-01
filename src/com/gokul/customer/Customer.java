package com.gokul.customer;

public class Customer {
    private int customerId;
    private String name;
    private double balance;
    private String password;
    private long acountId;
//constructor
    public Customer(int customerId, String name, double balance, String password, long acountId) {
        this.customerId = customerId;
        this.name = name;
        this.balance = balance;
        this.password = password;
        this.acountId = acountId;
    }

    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAcountId() {
        return acountId;
    }

    public void setAcountId(long acountId) {
        this.acountId = acountId;
    }

    @Override
    public String toString() {
        return customerId +
                " " + name +
                " " + balance +
                " " + password +
                " " + acountId ;
    }
}
