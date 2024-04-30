package com.ps;
import java.sql.Time;
import java.util.Date;

public class Transaction {
    private String dateOfTransaction;
    private String timeOfTransaction;

    private String description;
    private String vendor;
    private float amount;

    public Transaction(String dateOfTransaction, String timeOfTransaction, String description, String vendor, float amount) {
        this.dateOfTransaction = dateOfTransaction;
        this.timeOfTransaction = timeOfTransaction;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public void setTimeOfTransaction(String timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction='" + dateOfTransaction + '\'' +
                ", timeOfTransaction='" + timeOfTransaction + '\'' +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }
}

