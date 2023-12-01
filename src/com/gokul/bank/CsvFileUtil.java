package com.gokul.bank;

import java.io.*;
import java.util.*;

import com.gokul.customer.Customer;

public class CsvFileUtil {

    static CsvFileUtil csvFileUtil;
    public static CsvFileUtil getInstance() {
        if(csvFileUtil==null) {
            csvFileUtil=new CsvFileUtil();
        }
        return csvFileUtil;
    }
    private static final String filePath="/Users/srinivasan/Downloads/BankingApplication/src/band_db.txt";

    public void Read() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Bank.customerList=new ArrayList<>();
            Bank.customermap=new HashMap<>();
            while ((line = br.readLine()) != null) {
                Customer obj=castcustomer(line);

                Bank.customerList.add(obj);
                Bank.customermap.put(obj.getCustomerId(),obj);

                Bank.refAccountId = (int) Bank.customerList.get(Bank.customerList.size()-1).getAcountId();
                Bank.refCustId = Bank.customerList.get(Bank.customerList.size()-1).getCustomerId();

            }
            br.close();

        }


    }
    private Customer castcustomer(String line){
        String[] parts = line.split(" ");
        Customer cus=new Customer();
        cus.setCustomerId(Integer.parseInt(parts[0]));
        cus.setName(parts[1]);
        cus.setBalance(Double.parseDouble(parts[2]));
        cus.setPassword(parts[3]);
        cus.setAcountId(Long.parseLong(parts[4]));
        return cus;
    }


        public void writeCsv(Customer customer) throws IOException {
        FileWriter pw=null;
            try{
                pw=new FileWriter(filePath,true);
                pw.write("\n"+customer.toString());
            }
            catch (IOException e){
               e.printStackTrace();
            }
            finally {
                if(pw!=null){
                    pw.close();
                }
            }

        }
    public void reportCsv() throws IOException {
        PrintWriter pw=null;
        try{
            pw=new PrintWriter(filePath);
            Set keyset=Bank.customermap.keySet();
            Iterator iterator=keyset.iterator();
            while(iterator.hasNext()){
                int cusId= (int) iterator.next();
                Customer customer1=Bank.customermap.get(cusId);
                pw.println(customer1.toString());
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(pw!=null){
                pw.close();
            }
        }

    }


    }


