package com.gokul.customer;

import com.gokul.Transaction.Transaction;
import com.gokul.Transaction.TransactionReport;
import com.gokul.bank.Bank;
import com.gokul.customer.Customer;

import java.io.IOException;

public class TransactionAction {

    public void deposit(int customer_id,double amount) throws IOException {
        if(amount<0){
            return;
        }
        Customer cus= Bank.customermap.get(customer_id);
        cus.setBalance(cus.getBalance()+amount);
        System.out.println("Deposited amount :"+amount);
        System.out.println("Current balance :"+cus.getBalance());
        //persistence logic for file
        Bank.customermap.put(customer_id,cus);
        TransactionReport t=new TransactionReport();
        int getLastTransaction= t.getLastTransaction(customer_id);
        Transaction trans=new Transaction(getLastTransaction+1,"Deposit",amount,cus.getBalance());
        t.writeTransaction(customer_id,trans);

    }
    public boolean withdraw(int customer_id,double amount) throws IOException {
        Customer cus=Bank.customermap.get(customer_id);
        double d=cus.getBalance()-amount;
        if(d>=500){
            cus.setBalance(d);
            System.out.println("Withdraw amount :"+amount);
            System.out.println("Current balance :"+d);
            //persistence logic for file
            Bank.customermap.put(customer_id,cus);
            TransactionReport t=new TransactionReport();
            int getLastTransaction= t.getLastTransaction(customer_id);
            Transaction trans=new Transaction(getLastTransaction+1,"Withdraw",amount,cus.getBalance());
            t.writeTransaction(customer_id,trans);
            return true;
        }
        System.out.println("Insufficient fund");
        System.out.println("Current balance :"+cus.getBalance());
        System.out.println("minimum balance should be maintain : 500");
        return false;
    }
    public void transfer(int fromCusId,int toCusId,double amount) throws IOException {
        Customer tocus=Bank.customermap.get(toCusId);
        if(tocus==null){
            System.out.println("The customer to transfer fund doesnot exists");
            return;
        }
        Customer fromcus=Bank.customermap.get(fromCusId);

        boolean isSucess=withdraw(fromCusId,amount);
        if(isSucess){
            deposit(toCusId,amount);
        }

    }

}
