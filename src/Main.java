import com.gokul.Transaction.TransactionReport;
import com.gokul.bank.CsvFileUtil;
import com.gokul.bank.CustomerHandler;
import com.gokul.customer.TransactionAction;

import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        CsvFileUtil csv=new CsvFileUtil();
        csv.Read();
//       CustomerHandler c=new CustomerHandler();
//       c.addCustomer();
        printMenu();
        csv.reportCsv();





    }
    static void printMenu(){
        System.out.println("please select an option"+"\n 1-Create user \n 2-Deposit \n 3-withdraw \n 4-Fund transfer \n 5-Transaction history");
        try{
            Scanner sc=new Scanner(System.in);
            int option=sc.nextInt();
            CustomerHandler c=new CustomerHandler();
            TransactionAction t=new TransactionAction();
            switch(option){
                case 1:
                    c.addCustomer();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    fundTransfer();
                    break;
                case 5:
                    transactionHistory();
                    break;
                default:
                    System.out.println("Invalid Option");

            }
        }
        catch(Exception e){
            System.out.println("Invalid Input");
        }
    }

    private static void transactionHistory() {
        System.out.println("*************** Transaction History *********************");
        CustomerHandler c=new CustomerHandler();
        TransactionAction t=new TransactionAction();
        Scanner sc=new Scanner (System.in);
        //Authentication
        System.out.println("Enter the customerId :");
        int cusid=sc.nextInt();
        System.out.println("Enter the password :");
        String password2=sc.next();
        if(c.authenticateCustomer(cusid,password2)) {
            TransactionReport tr = new TransactionReport();
            tr.transactionHistory(cusid);
        }
    }

    private static void fundTransfer() throws IOException {
        {
            CustomerHandler c=new CustomerHandler();
            TransactionAction t=new TransactionAction();
            Scanner sc=new Scanner (System.in);
            //Authentication
            System.out.println("Enter the customerId :");
            int cusid=sc.nextInt();
            System.out.println("Enter the password :");
            String password=sc.next();
            System.out.println("Enter cus id to transfer");
            int toCusId=sc.nextInt();
            if(c.authenticateCustomer(cusid,password)){
                System.out.println("Enter the amount :");
                double amount=sc.nextDouble();
                t.transfer(cusid,toCusId,amount);

            }

        }
    }


    private static void withdraw() throws IOException {
        CustomerHandler c=new CustomerHandler();
        TransactionAction t=new TransactionAction();
        Scanner sc=new Scanner (System.in);
        //Authentication
        System.out.println("Enter the customerId :");
        int cusid=sc.nextInt();
        System.out.println("Enter the password :");
        String password2=sc.next();
        if(c.authenticateCustomer(cusid,password2)){
            System.out.println("Enter the amount :");
            double amount=sc.nextDouble();
            t.withdraw(cusid,amount);
        }
    }


    private static void deposit() throws IOException {
        CustomerHandler c = new CustomerHandler();
        TransactionAction t = new TransactionAction();
        Scanner sc = new Scanner(System.in);
        //Authentication
        System.out.println("Enter the customerId :");
        int cusid = sc.nextInt();
        System.out.println("Enter the password :");
        String password = sc.next();
        if (c.authenticateCustomer(cusid, password)) {
            System.out.println("Enter the amount :");
            double amount1 = sc.nextDouble();
            t.deposit(cusid, amount1);
        }

    }
}

