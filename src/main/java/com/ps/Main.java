package com.ps;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class Main {



    public static void main(String[] args) {

        ArrayList<Transaction> storeInventory = new ArrayList<>();




        Scanner scanner = new Scanner(System.in);
        String firstOption;
        String ledgerOption;



        // File reader for transactions.txt
        try {
            BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"));
            String input;
            while ((input = bufreader.readLine()) != null){


                String[] splitInput = input.split("\\|");
                LocalDate dateOfTransaction = LocalDate.parse(splitInput[0]);
                LocalTime timeOfTransaction = LocalTime.parse(splitInput[1]);
                String description = splitInput[2];
                String vendor = splitInput[3];
                float amount = Float.parseFloat(splitInput[4]);

                Transaction transaction = new Transaction(dateOfTransaction,timeOfTransaction, description, vendor, amount);
                storeInventory.add(transaction);


            }
            bufreader.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }


        do{

            // Main menu
            System.out.println("\nWelcome! Choose between these options: ");
            System.out.println("\tD) Add Deposit: ");
            System.out.println("\tP) Make Payment (With Debit): ");
            System.out.println("\tL) Ledger: ");
            System.out.println("\tX) Exit: ");

            firstOption = scanner.nextLine().toUpperCase();




            switch (firstOption){


                // Deposit feature
                case "D":
                    String depositDescription;
                    String depositVendor;

                    float depositAmount;

                    System.out.println("\n Type the description of the deposit here: ");
                    depositDescription = scanner.nextLine();

                    System.out.println("\n Type the name of the vendor here: ");
                    depositVendor = scanner.nextLine();

                    System.out.println("\n Type amount of the deposit here: ");
                    depositAmount = scanner.nextFloat();

                    LocalDate dateOfTransaction = LocalDate.now();
                    LocalTime timeOfTransaction = LocalTime.now().withNano(0);

                    Transaction newDeposit = new Transaction(dateOfTransaction, timeOfTransaction, depositDescription, depositVendor, depositAmount);
                    storeInventory.add(newDeposit);

                    writeToFile("transactions.txt",String.format("\n%s|%s|%s|%s|%.2f\n",
                    dateOfTransaction,
                    timeOfTransaction,
                    newDeposit.getDescription(),
                    newDeposit.getVendor(),
                    newDeposit.getAmount()));

                    System.out.println("Deposit added!");
                    break;

                // Payment/withdrawal feature
                case "P":


                    String paymentDescription;
                    String paymentVendor;
                    float paymentAmount;
                    System.out.println("\n Type the description of your withdrawal here: ");
                    paymentDescription = scanner.nextLine();

                    System.out.println("\n Type the name of the vendor here: ");
                    paymentVendor = scanner.nextLine();

                    System.out.println("\n Type amount of your withdrawal here: ");
                    paymentAmount = -scanner.nextFloat();


                     dateOfTransaction = LocalDate.now();
                     timeOfTransaction = LocalTime.now().minusSeconds(0).withNano(0);

                    Transaction newPayment = new Transaction(dateOfTransaction, timeOfTransaction, paymentDescription, paymentVendor, paymentAmount);
                    storeInventory.add(newPayment);

                    System.out.println("Payment added!");
                    break;

                case "L":
                    System.out.println("\tA) Display all entries: ");
                    System.out.println("\tD) Display all deposits into the account: ");
                    System.out.println("\tP) Display all payments from this account: ");
                    System.out.println("\tR) Display entries by Date or by vendor: ");

                    ledgerOption = scanner.nextLine().toUpperCase();

                    switch (ledgerOption){
                        case "A":
                            for (Transaction transaction : storeInventory) {
                                System.out.printf("Date:%-10s       Time:%-10s       Description:%-30s       Vendor:%-15s       Amount:$%.2f\n",
                                        transaction.getDateOfTransaction(),
                                        transaction.getTimeOfTransaction(),
                                        transaction.getDescription(),
                                        transaction.getVendor(),
                                        transaction.getAmount());

                            }



                    }




                default:
            }



        } while(!firstOption.equalsIgnoreCase("x"));


    }

    public static void readerMethod(){

    }
    public static void writeToFile(String path, String str){
        try {

            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(path, true));
            bufWriter.write(str);
            bufWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}