package com.gokul.Transaction;

import com.gokul.Transaction.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TransactionReport {
    public void writeTransaction(int customerId, Transaction t) throws IOException {
        String filename = customerId + ".txt";

        FileWriter pw = null;
        try {
            File file = new File(filename);
            boolean isnewfile = false;
            if (!file.exists()) {
                isnewfile = true;
            }
            pw = new FileWriter(filename, true);
            if(!isnewfile){
              pw.write("\n");
            }
            pw.write(t.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            pw.close();
        }
    }
    public int getLastTransaction(int customerId){
        String filename = customerId + ".txt";
        File pw =new File(filename);
        try {
           Scanner sc=new Scanner(pw);
           String lastline="";
           while(sc.hasNext()){
               lastline=sc.nextLine();
           }
           sc.close();
           Transaction t=castString(lastline);
           return t.getTransaction_id();
        } catch (Exception e) {
            System.out.println("Exception"+ e);
        }
        return 0;

    }
    private Transaction castString(String str){
        String[] part=str.split("\t");
        return new Transaction(Integer.parseInt(part[0]),
                part[1],
                Double.parseDouble(part[2]),
                Double.parseDouble(part[3]));
    }
    public void transactionHistory(int customerId){
        String filename = customerId + ".txt";
        File pw =new File(filename);
        try {
            Scanner sc=new Scanner(pw);
            String lastline="";
            while(sc.hasNext()){
                lastline=sc.nextLine();
                System.out.println(lastline);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Exception"+ e);
        }

    }
}
