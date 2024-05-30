package com.mycompany.healthsystemapi.model;

/**
 * Represents a patient in the health system.
 * 
 * A patient's object includes information such as ID, name, contact info, address, medical history and health status.
 * It extends the Person class.
 * 
 * @author rachelcooray
 */
public class Patient extends Person{
    // Attributes of this class
    private String medicalHistory;
    private String currentHealthStatus;

    /**
     * Constructs a patient object with the specified ID and other attributes.
     */
    public Patient(String medicalHistory, String currentHealthStatus, int id, String name, String contactInfo, String address) {
        super(id, name, contactInfo, address);
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }
    
    /**
     * Default constructor for the class.
     */
    public Patient() {
        super(); // Call the default constructor of the superclass (Person)
    }

    // Getters and setters of each attribute
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
    
    
}
