package com.gokul.bank;

import com.gokul.Transaction.Transaction;
import com.gokul.Transaction.TransactionReport;
import com.gokul.customer.Customer;

import java.io.IOException;
import java.util.Scanner;

//Task 2
public class CustomerHandler {

    public void addCustomer() throws IOException {

        Scanner sc =new Scanner(System.in);
        System.out.println("Enter the User Name :");
        String uname=sc.nextLine();
        System.out.println("Enter the Password :");
        String pass=sc.nextLine();
        System.out.println("Enter the Re-type password :");
        String repass=sc.nextLine();
        if(!pass.equals(repass)){
            System.out.println("password mismatch retype password  !!");
            return;
        }
        if(!isValidPassword(pass)){
            System.out.println("Invalid Password");

            return;
        }

        String encpass=getEncryptedPassword(pass);

        Customer c = new Customer(Bank.refCustId+1, uname, Bank.initial_balance, encpass, Bank.refAccountId+1);
        Bank.customerList.add(c);
        Bank.customermap.put(c.getCustomerId(),c);
        System.out.println("New customer onboarded ");
        System.out.println(Bank.customerList.get(Bank.customerList.size()-1));
        CsvFileUtil.getInstance().writeCsv(c);
        System.out.println("customer Info is added in the file");
        logTransaction(c.getCustomerId());

    }

    private void logTransaction(int customerId) throws IOException {
        Transaction trans=new Transaction(1,"opening",500,500);
        TransactionReport tr=new TransactionReport();
        tr.writeTransaction(customerId,trans);

    }

    private boolean isValidPassword(String password){
        char []arr=password.toCharArray();
        for(char i:arr){
            if((i>=97&&i<=122)||(i>=65&&i<=90)||(i>=48&&i<=57)) {
                return true;
            }
        }
        return false;
    }
    private String getEncryptedPassword(String password){
        char[] arr=password.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(arr[i]=='z'||arr[i]=='Z'||arr[i]=='9') {
                switch (arr[i]) {
                    case 'z':
                        arr[i] = 'a';
                        break;
                    case 'Z':
                        arr[i] = 'A';
                        break;
                    case '9':
                        arr[i] = '0';
                        break;
                }
            }
            else{
                arr[i]=(char)(arr[i]+1);
            }
        }
        String result=String.valueOf(arr);
        return result;

    }
    public boolean authenticateCustomer(int customerId,String password){
        String encrypeted=getEncryptedPassword(password);
        Customer c=Bank.customermap.get(customerId);
        if(c==null){
            System.out.println("Invalid customer Id");
            return false;
        }
        if(encrypeted.equals(c.getPassword())){
            System.out.println("valid user");
            return true;
        }
        else{
            System.out.println("Invalid password");

        }
        return true;
    }
}
