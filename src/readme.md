# Capstone One Accounting Ledger 
### ~Selam Nur
https://github.com/selamnur7/CapstoneOne_AccountingLedger/blob/main/src/main/java/com/ps/Main.java
### Description
This project allows the user to store and save the transactions that they do everyday.
The program tracks the time that the transaction was saved, along with the time, the vendor, description of the transaction, and the amount. The features that this program has are:
- Add Deposit
  - 
  * After selecting the add deposit menu, the user is prompted to type the description of 
  the deposit, along with the name of the vendor, and the amount of the deposit 

![img_1.png](img_1.png) 
  * When the user completes their deposit the program requests if they
would like to add another deposit or not. If they choose yes, they will be prompted again
the same, if not, they will go back to the main menu
  ![img_2.png](img_2.png)
  * This code adds the new deposits into the storeInventory arraylist, and 
  writes the new deposits into the transactions.txt file
- Withdrawal/ Payment
  - 
  * Similar to the add deposit menu, this feature requests the description of the payment,
  along with the name of the vendor and the amount of the payment

![img_4.png](img_4.png)
  * It also saves the new payment into the arraylist and writes to the text file
- Ledger Menu
  -
  * This menu displays options to print all entries, deposits, payments or goes to reports
![img_5.png](img_5.png)
  * All of the transactions are sorted from earliest to oldest for each option, and cycles through all transactions in the arraylist 
depending on what is being searched for 
![img_7.png](img_7.png)
  * This menu is when the user requests all the deposits in the transactions arrayList
  * If the user presses h they go back to the main menu, but if they press r, they go to reports

- Reports Menu
  -
  * Reports menu gives you the option to search by this month, last month, this year, last year,
  search by vendor, custom search, or go back to the ledger page
![img_8.png](img_8.png)
  * This code sorts the transactions from earliest to latest and loops through the arraylist until it finds 
a transaction in this current month
![img_9.png](img_9.png)
  * Here you can see all of the options that the user is given and the prompts they give to get to the Month to date Report
  * After it gets printed the user can search using another filter, or go back to the ledger page


- Custom Search
  -
![img_10.png](img_10.png)
  * This is the custom filter that will print out one specific transaction that the user types all the attributes for.
    This can be useful for bank accounts that have been open for years and are trying to keep the scope of the search very small.
![img_11.png](img_11.png)
  * I found this code the most interesting because I had to compare many different attributes in the Transaction class
along with converting one from a LocalDate data type to an int in order to get the custom search to work. Once I got one of them
down it was easier to do the rest.

## Thanks for viewing my Project!