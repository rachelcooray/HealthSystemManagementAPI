package com.mycompany.healthsystemapi.model;

/**
 * Represents a billing object in the health system.
 * 
 * A billing object includes information such as ID, patient details, amount, payment date and balance.
 * 
 * @author rachelcooray
 */
public class Billing {
    // Attributes of this class
    private int id;
    private int patientId;
    private double amount;
    private String status;
    private String invoiceDate;
    private String paymentDate;
    private double outstandingBalance;

    /**
     * Constructs a billing object with the specified ID and other attributes.
     */
    public Billing(int id, int patientId, double amount, String status, String invoiceDate, String paymentDate, double outstandingBalance) {
        this.id = id;
        this.patientId = patientId;
        this.amount = amount;
        this.status = status;
        this.invoiceDate = invoiceDate;
        this.paymentDate = paymentDate;
        this.outstandingBalance = outstandingBalance;
    }
    
    /**
     * Default constructor for the class.
     */
    public Billing(){
        
    }

    // Getters and setters of each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }
    
    
}

