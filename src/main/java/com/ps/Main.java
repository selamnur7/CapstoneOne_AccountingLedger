package com.ps;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;


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
            while ((input = bufreader.readLine()) != null) {


                String[] splitInput = input.split("\\|");
                LocalDate dateOfTransaction = LocalDate.parse(splitInput[0]);
                LocalTime timeOfTransaction = LocalTime.parse(splitInput[1]);
                String description = splitInput[2];
                String vendor = splitInput[3];
                float amount = Float.parseFloat(splitInput[4]);

                Transaction transaction = new Transaction(dateOfTransaction, timeOfTransaction, description, vendor, amount);
                storeInventory.add(transaction);


            }
            bufreader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        do {

            // Main menu
            System.out.println("\nWelcome! Choose between these options: ");
            System.out.println("\tD) Add Deposit: ");
            System.out.println("\tP) Make Payment (With Debit): ");
            System.out.println("\tL) Ledger: ");
            System.out.println("\tX) Exit: ");

            firstOption = scanner.nextLine().toUpperCase();


            switch (firstOption) {


                // Deposit feature
                case "D":
                    String depositMenu;
                    do {
                        String depositDescription;
                        String depositVendor;

                        float depositAmount;

                        System.out.println("\n Type the description of the deposit here: ");
                        depositDescription = scanner.nextLine();

                        System.out.println("\n Type the name of the vendor here: ");
                        depositVendor = scanner.nextLine();

                        System.out.println("\n Type the amount of the deposit here: ");
                        depositAmount = scanner.nextFloat();
                        scanner.nextLine();

                        // Makes dateOfTransaction and timeOfTransaction the current time
                        LocalDate dateOfTransaction = LocalDate.now();
                        LocalTime timeOfTransaction = LocalTime.now().withNano(0);

                        Transaction newDeposit = new Transaction(dateOfTransaction, timeOfTransaction, depositDescription, depositVendor, depositAmount);
                        storeInventory.add(newDeposit);

                        // Writes the new imputed deposit into the transactions.txt file
                        writeToFile("transactions.txt", String.format("\n%s|%s|%s|%s|%.2f",
                                dateOfTransaction,
                                timeOfTransaction,
                                newDeposit.getDescription(),
                                newDeposit.getVendor(),
                                newDeposit.getAmount()));

                        // Loops the menu until user chooses to leave
                        System.out.println("Deposit added!");
                        System.out.println("Would you like to add another deposit?(Y/N)");

                        depositMenu = scanner.nextLine().toUpperCase();


                    } while (depositMenu.equals("Y"));
                    break;

                // Payment/withdrawal feature
                case "P":
                    String paymentMenu;
                    LocalDate dateOfTransaction;
                    LocalTime timeOfTransaction;
                    // Loops the payment menu

                    do {

                        String paymentDescription;
                        String paymentVendor;
                        float paymentAmount;
                        System.out.println("\n Type the description of your withdrawal here: ");
                        paymentDescription = scanner.nextLine();

                        System.out.println("\n Type the name of the vendor here: ");
                        paymentVendor = scanner.nextLine();

                        System.out.println("\n Type amount of your withdrawal here: ");
                        paymentAmount = -scanner.nextFloat();
                        scanner.nextLine();

                        // Makes dateOfTransaction and timeOfTransaction the current time
                        dateOfTransaction = LocalDate.now();
                        timeOfTransaction = LocalTime.now().withNano(0);

                        Transaction newPayment = new Transaction(dateOfTransaction, timeOfTransaction, paymentDescription, paymentVendor, paymentAmount);
                        storeInventory.add(newPayment);

                        // Writes to file the new imputed payment
                        writeToFile("transactions.txt", String.format("\n%s|%s|%s|%s|%.2f",
                                dateOfTransaction,
                                timeOfTransaction,
                                newPayment.getDescription(),
                                newPayment.getVendor(),
                                newPayment.getAmount()));

                        // Loops until user does not press Y
                        System.out.println("Payment added!");
                        System.out.println("Would you like to add another payment?(Y/N)");

                        paymentMenu = scanner.nextLine().toUpperCase();

                    } while (paymentMenu.equals("Y"));
                    break;


                case "L":
                    // loop for ledger
                    do {
                        System.out.println(" ");
                        System.out.println("\tA) Display all entries: ");
                        System.out.println("\tD) Display all deposits into the account: ");
                        System.out.println("\tP) Display all payments from this account: ");
                        System.out.println("\tR) Display entries by Date or by vendor: ");
                        System.out.println("\tH) Go back to home page: ");


                        ledgerOption = scanner.nextLine().toUpperCase();


                        switch (ledgerOption) {
                            case "A":

                                // Puts all the transactions in order from earliest to oldest
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
                                    if (transaction.getAmount() > 0) {
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
                                    if (transaction.getAmount() < 0) {
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
                                do {

                                    System.out.println("\nChoose one of the options to run");
                                    System.out.println("\t1) Month to date: ");
                                    System.out.println("\t2) Previous month: ");
                                    System.out.println("\t3) Year to Date: ");
                                    System.out.println("\t4) Previous Year: ");
                                    System.out.println("\t5) Search by vendor: ");
                                    System.out.println("\t6) Custom Search: ");
                                    System.out.println("\t0) Go back to ledger page: ");

                                    reportsOption = scanner.nextInt();
                                    scanner.nextLine();


                                    switch (reportsOption) {
                                        case 1:
                                            Collections.sort(storeInventory, Comparator.comparing(Transaction::getDateOfTransaction).thenComparing(Transaction::getTimeOfTransaction).reversed());
                                            System.out.println("                                                ****    Month To Date Report     ****    ");
                                            System.out.println("                                                    #     -- Transactions --   #           ");
                                            System.out.println(" ");

                                            for (Transaction transaction : storeInventory) {

                                                // Compares all the transaction dates with the current month
                                                if (LocalDate.now().getMonthValue() == transaction.getDateOfTransaction().getMonthValue()
                                                        && transaction.getDateOfTransaction().getYear() == LocalDate.now().getYear()) {

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
                                            System.out.println("                                                ****       Last Month's Report      ****    ");
                                            System.out.println("                                                    #     -- Transactions --   #           ");
                                            System.out.println(" ");
                                            for (Transaction transaction : storeInventory) {

                                                // Compares all transactions with the previous month to only print those
                                                if (transaction.getDateOfTransaction().getMonthValue() == LocalDate.now().minusMonths(1).getMonthValue()
                                                        && transaction.getDateOfTransaction().getYear() == LocalDate.now().getYear()) {

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
                                            System.out.println("                                                ****    Year To Date Report      ****    ");
                                            System.out.println("                                                    #     -- Transactions --   #           ");
                                            System.out.println(" ");
                                            for (Transaction transaction : storeInventory) {

                                                // Only prints transactions from this year loop
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

                                            // Fancy font
                                            System.out.println("                                                ****    Previous Year's Report     ****    ");
                                            System.out.println("                                                    #     -- Transactions --   #           ");
                                            System.out.println(" ");
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

                                            // Asks for name of vendor then will loop from every transaction to find the vendor name matching
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
                                        case 6:
                                            System.out.println("What is the start date? Enter using (yyyy-MM-DD)");
                                            String customStartDate = scanner.next();
                                            System.out.println("What is the end date? Enter using (yyyy-MM-DD)");
                                            String customEndDate = scanner.next();
                                            System.out.println("What is the description of the transaction?");
                                            String customDescription = scanner.next();
                                            System.out.println("What is the vendors name?");
                                            String customVendor = scanner.next();
                                            System.out.println("What is the amount?");
                                            float customAmount = scanner.nextFloat();

                                            LocalDate startDateMatch = LocalDate.parse(customStartDate);
                                            LocalDate endDateMatch = LocalDate.parse(customEndDate);


                                            for (Transaction transaction : storeInventory) {
                                                if (transaction.getDateOfTransaction().compareTo(startDateMatch) >= 0 &&
                                                        transaction.getDateOfTransaction().compareTo(endDateMatch) <= 0 &&
                                                        transaction.getDescription().equalsIgnoreCase(customDescription) &&
                                                        transaction.getVendor().equalsIgnoreCase(customVendor) &&
                                                        transaction.getAmount() == customAmount) {

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
                                } while (reportsOption != 0);


                        }
                    } while (!ledgerOption.equalsIgnoreCase("H"));


                default:
                    break;
            }


        } while (!firstOption.equalsIgnoreCase("x"));


    }

    // write to file method
    public static void writeToFile(String path, String str) {
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