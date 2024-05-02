package com.ps;
import java.sql.Time;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate dateOfTransaction;
    private LocalTime timeOfTransaction;

    private String description;
    private String vendor;
    private float amount;

    public Transaction(LocalDate dateOfTransaction, LocalTime timeOfTransaction, String description, String vendor, float amount) {
        this.dateOfTransaction = dateOfTransaction;
        this.timeOfTransaction = timeOfTransaction;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(LocalDate dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public LocalTime getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public void setTimeOfTransaction(LocalTime timeOfTransaction) {
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
                "description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }
}
