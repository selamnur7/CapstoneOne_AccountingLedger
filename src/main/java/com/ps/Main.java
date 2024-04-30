package com.ps;
import java.io.BufferedReader;
import java.text.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Main {

    public static void main(String[] args) {

        ArrayList<Transaction> storeInventory = new ArrayList<>();


        Scanner scanner = new Scanner(System.in);
        String firstOption;

        try {
            BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"));
            String input;
            while ((input = bufreader.readLine()) != null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String[] splitInput = input.split("\\|");
                String dateOfTransaction = splitInput[0];
                String timeOfTransaction = splitInput[1];
                String description = splitInput[2];
                String vendor = splitInput[3];
                float amount = Float.parseFloat(splitInput[4]);

                Transaction transaction = new Transaction(dateOfTransaction, timeOfTransaction, description, vendor, amount);


            }
            bufreader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        do{
            System.out.println("\nWelcome! Choose between these options: ");
            System.out.println("\tD) Add Deposit: ");
            System.out.println("\tP) Make Payment (With Debit): ");
            System.out.println("\tL) Ledger: ");
            System.out.println("\tX) Exit: ");

            firstOption = scanner.nextLine().toUpperCase();




            switch (firstOption){

                case "D":
                    String newDescription;
                    String newVendor;
                    float newAmount;
                    System.out.println("\n Type the description of the deposit here: ");
                    newDescription = scanner.nextLine();

                    System.out.println("\n Type the name of the vendor here: ");
                    newVendor = scanner.nextLine();

                    System.out.println("\n Type amount of the deposit here: ");
                    newAmount = scanner.nextFloat();

                    String depositDate = String.valueOf(LocalDate.now());
                    String depositTime = String.valueOf(LocalTime.now());

                    Transaction newDeposit = new Transaction(depositDate, depositTime, newDescription, newVendor, newAmount);
                    storeInventory.add(newDeposit);

                    System.out.println("Deposit added!");
                    System.out.println(storeInventory);
                    break;

            }



        } while(!firstOption.equalsIgnoreCase("x"));


    }
}