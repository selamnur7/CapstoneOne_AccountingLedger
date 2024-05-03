package com.ps;
import java.io.*;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Main {



    public static void main(String[] args) {

        ArrayList<Transaction> storeInventory = new ArrayList<>();




        Scanner scanner = new Scanner(System.in);
        String firstOption;
        String ledgerOption;
        int reportsPage = 0;
        int reportsOption;




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

                    writeToFile("transactions.txt",String.format("\n%s|%s|%s|%s|%.2f",
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
                     timeOfTransaction = LocalTime.now().withNano(0);

                    Transaction newPayment = new Transaction(dateOfTransaction, timeOfTransaction, paymentDescription, paymentVendor, paymentAmount);
                    storeInventory.add(newPayment);

                    writeToFile("transactions.txt",String.format("\n%s|%s|%s|%s|%.2f",
                    dateOfTransaction,
                    timeOfTransaction,
                    newPayment.getDescription(),
                    newPayment.getVendor(),
                    newPayment.getAmount()));

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
                        Collections.sort(storeInventory, Comparator.comparing(Transaction::getDateOfTransaction).thenComparing(Transaction::getTimeOfTransaction).reversed());
                            for (Transaction transaction : storeInventory) {
                                System.out.printf("Date:%-10s       Time:%-10s       Description:%-30s       Vendor:%-15s       Amount:$%.2f\n",
                                        transaction.getDateOfTransaction(),
                                        transaction.getTimeOfTransaction(),
                                        transaction.getDescription(),
                                        transaction.getVendor(),
                                        transaction.getAmount());


                            }
                        break;

                        case "D":
                        Collections.sort(storeInventory, Comparator.comparing(Transaction::getDateOfTransaction).thenComparing(Transaction::getTimeOfTransaction).reversed());
                            for (Transaction transaction : storeInventory) {
                                if(transaction.getAmount() > 0){
                                    System.out.printf("Date:%-10s       Time:%-10s       Description:%-30s       Vendor:%-15s       Amount:$%.2f\n",
                                            transaction.getDateOfTransaction(),
                                            transaction.getTimeOfTransaction(),
                                            transaction.getDescription(),
                                            transaction.getVendor(),
                                            transaction.getAmount());
                                }

                            }
                        break;

                        case "P":
                            Collections.sort(storeInventory, Comparator.comparing(Transaction::getDateOfTransaction).thenComparing(Transaction::getTimeOfTransaction).reversed());
                            for (Transaction transaction : storeInventory) {
                                if(transaction.getAmount() < 0){
                                    System.out.printf("Date:%-10s       Time:%-10s       Description:%-30s       Vendor:%-15s       Amount:$%.2f\n",
                                            transaction.getDateOfTransaction(),
                                            transaction.getTimeOfTransaction(),
                                            transaction.getDescription(),
                                            transaction.getVendor(),
                                            transaction.getAmount());
                                }

                            }



                    break;
                        case "R":
                            if(reportsPage == 0) {
                                System.out.println("\nChoose one of the options to run");
                                System.out.println("\t1) Month to date: ");
                                System.out.println("\t2) Previous month: ");
                                System.out.println("\t3) Year to Date: ");
                                System.out.println("\t4) Previous Year: ");
                                System.out.println("\t5) Search by vendor: ");
                                System.out.println("\t0) Go back to reports page: ");

                                reportsOption = scanner.nextInt();

                        switch(reportsOption) {
                            case 1:
                                Collections.sort(storeInventory, Comparator.comparing(Transaction::getDateOfTransaction).thenComparing(Transaction::getTimeOfTransaction).reversed());
                                for (Transaction transaction : storeInventory) {
                                    if (LocalDate.now().getMonthValue() == transaction.getDateOfTransaction().getMonthValue() && transaction.getDateOfTransaction().getYear() == LocalDate.now().getYear()) {
                                        System.out.printf("Date:%-10s       Time:%-10s       Description:%-30s       Vendor:%-15s       Amount:$%.2f\n",
                                                transaction.getDateOfTransaction(),
                                                transaction.getTimeOfTransaction(),
                                                transaction.getDescription(),
                                                transaction.getVendor(),
                                                transaction.getAmount());
                                    }

                                }
                                break;
                            case 2:
                                Collections.sort(storeInventory, Comparator.comparing(Transaction::getDateOfTransaction).thenComparing(Transaction::getTimeOfTransaction).reversed());
                                for (Transaction transaction : storeInventory) {
                                    if (transaction.getDateOfTransaction().getMonthValue() == LocalDate.now().minusMonths(1).getMonthValue() && transaction.getDateOfTransaction().getYear() == LocalDate.now().getYear()) {
                                        System.out.printf("Date:%-10s       Time:%-10s       Description:%-30s       Vendor:%-15s       Amount:$%.2f\n",
                                                transaction.getDateOfTransaction(),
                                                transaction.getTimeOfTransaction(),
                                                transaction.getDescription(),
                                                transaction.getVendor(),
                                                transaction.getAmount());
                                    }

                                }

                                break;
                            case 3:
                                Collections.sort(storeInventory, Comparator.comparing(Transaction::getDateOfTransaction).thenComparing(Transaction::getTimeOfTransaction).reversed());
                                for (Transaction transaction : storeInventory) {
                                    if (transaction.getDateOfTransaction().getYear() == LocalDate.now().getYear()) {
                                        System.out.printf("Date:%-10s       Time:%-10s       Description:%-30s       Vendor:%-15s       Amount:$%.2f\n",
                                                transaction.getDateOfTransaction(),
                                                transaction.getTimeOfTransaction(),
                                                transaction.getDescription(),
                                                transaction.getVendor(),
                                                transaction.getAmount());
                                    }

                                }

                                break;
                            case 4:
                                Collections.sort(storeInventory, Comparator.comparing(Transaction::getDateOfTransaction).thenComparing(Transaction::getTimeOfTransaction).reversed());
                                for (Transaction transaction : storeInventory) {
                                    if (transaction.getDateOfTransaction().getYear() == LocalDate.now().minusYears(1).getYear()) {
                                        System.out.printf("Date:%-10s       Time:%-10s       Description:%-30s       Vendor:%-15s       Amount:$%.2f\n",
                                                transaction.getDateOfTransaction(),
                                                transaction.getTimeOfTransaction(),
                                                transaction.getDescription(),
                                                transaction.getVendor(),
                                                transaction.getAmount());
                                    }

                                }
                                break;
                            case 5:
                                Collections.sort(storeInventory, Comparator.comparing(Transaction::getDateOfTransaction).thenComparing(Transaction::getTimeOfTransaction).reversed());
                                System.out.println("What is the name of the vendor?");
                                String vendorSearch = scanner.next();

                                for (Transaction transaction : storeInventory) {
                                    if (vendorSearch.equals(transaction.getVendor())) {
                                        System.out.printf("Date:%-10s       Time:%-10s       Description:%-30s       Vendor:%-15s       Amount:$%.2f\n",
                                                transaction.getDateOfTransaction(),
                                                transaction.getTimeOfTransaction(),
                                                transaction.getDescription(),
                                                transaction.getVendor(),
                                                transaction.getAmount());
                                    }

                                }
                                break;


                        }

                                }
                            }


                default:
            break;
            }



        } while(!firstOption.equalsIgnoreCase("x"));


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