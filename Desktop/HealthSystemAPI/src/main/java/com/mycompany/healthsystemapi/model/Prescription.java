package com.mycompany.healthsystemapi.model;

/**
 * Represents a prescription in the health system.
 * 
 * A prescription object includes information such as ID, patient's details, medication, dosage, instructions and duration.
 * 
 * @author rachelcooray
 */
public class Prescription {
    // Attributes of this class
    private int id;
    private int patientId;
    private String medication;
    private String dosage;
    private String instructions;
    private String duration;

    /**
     * Constructs a prescription object with the specified ID and other attributes.
     */
    public Prescription(int id, int patientId, String medication, String dosage, String instructions, String duration) {
        this.id = id;
        this.patientId = patientId;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
    }
    
    /**
     * Default constructor for the class.
     */
    public Prescription(){
        
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

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    
}
