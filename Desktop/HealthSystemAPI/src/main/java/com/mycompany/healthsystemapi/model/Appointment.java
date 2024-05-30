package com.mycompany.healthsystemapi.model;

/**
 * Represents an appointment in the health system.
 * 
 * An appointment includes information such as ID, date and time, patient details, and doctor details.
 *
 * @author rachelcooray
 */
public class Appointment {
    // Attributes of this class
    private int id;
    private String dateTime;
    private Patient patient;
    private Doctor doctor;

    /**
     * Constructs an appointment object with the specified ID, date and time, patient details, and doctor details.
     */
    public Appointment(int id, String dateTime, Patient patient, Doctor doctor) {
        this.id = id;
        this.dateTime = dateTime;
        this.patient = patient;
        this.doctor = doctor;
    }
    
    /**
     * Default constructor for the class.
     */
    public Appointment(){
        
    }

    // Getters and setters of each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
     
}