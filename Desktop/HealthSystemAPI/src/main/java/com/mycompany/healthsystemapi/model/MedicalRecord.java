package com.mycompany.healthsystemapi.model;

/**
 * Represents a medical record in the health system.
 * 
 * @author rachelcooray
 */
public class MedicalRecord {
    // Attributes of this class
    private int id;
    private int patientId;
    private String recordDetails;
    private String converingDiagnose;
    private String treatment;
    private String otherData;

    /**
     * Constructs a medical record object with the specified ID and other attributes.
     */
    public MedicalRecord(int id, int patientId, String recordDetails, String converingDiagnose, String treatment, String otherData) {
        this.id = id;
        this.patientId = patientId;
        this.recordDetails = recordDetails;
        this.converingDiagnose = converingDiagnose;
        this.treatment = treatment;
        this.otherData = otherData;
    }
    
    /**
     * Default constructor for the class.
     */
    public MedicalRecord(){
        
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

    public String getRecordDetails() {
        return recordDetails;
    }

    public void setRecordDetails(String recordDetails) {
        this.recordDetails = recordDetails;
    }

    public String getConveringDiagnose() {
        return converingDiagnose;
    }

    public void setConveringDiagnose(String converingDiagnose) {
        this.converingDiagnose = converingDiagnose;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }
       
    
}

