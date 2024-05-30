package com.mycompany.healthsystemapi.dao;

// Import required classes and libraries
import com.mycompany.healthsystemapi.model.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Access Object (DAO) for managing medical records in the health system.
 * Provides methods for CRUD operations on medical records.
 * 
 * @author rachelcooray
 */
public class MedicalRecordDAO {
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordDAO.class); // For logging messages
    
    // Initializing some demo medical records
    static {
        medicalRecords.add(new MedicalRecord(1, 1, "Record details for patient 1", "Diabetes", "Insulin therapy", "None"));
        medicalRecords.add(new MedicalRecord(2, 2, "Record details for patient 2", "Hypertension", "Lifestyle modifications", "None"));
    }
    
    /**
     * Retrieves all medical records by returning a list.
     */
    public List<MedicalRecord> getAllMedicalRecords() {
        try {
            return medicalRecords;
        } catch (Exception ex) {
            logger.error("Error occurred while retrieving all medical records: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve medical records.", ex);
        }
    }

    /**
     * Retrieves the medical record with the specified ID.
     */
    public MedicalRecord getMedicalRecordById(int id) {
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getId() == id) {
                return medicalRecord;
            }
        }
        return null;
    }
    
    /**
     * Retrieves medical records associated with a specific patient.
     */
    public List<MedicalRecord> getMedicalRecordsByPatientId(int patientId) {
        List<MedicalRecord> patientMedicalRecords = new ArrayList<>();
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getPatientId() == patientId) {
                patientMedicalRecords.add(medicalRecord);
            }
        }
        return patientMedicalRecords;
    }
    
    /**
     * Adds a new medical record to the list of medical records.
     */
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        int newMedicalRecordId = getNextMedicalRecordId();
        medicalRecord.setId(newMedicalRecordId);
        medicalRecords.add(medicalRecord);
        logger.info("Medical Record added: {}", medicalRecord);
    }

    /**
     * Updates an existing medical record.
     */
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        for (int i = 0; i < medicalRecords.size(); i++) {
            MedicalRecord medicalRecord = medicalRecords.get(i);
            if (medicalRecord.getId() == updatedMedicalRecord.getId()) {
                medicalRecords.set(i, updatedMedicalRecord);
                logger.info("Medical Record updated: {}", updatedMedicalRecord);
                return;
            }
        }
    }

    /**
     * Deletes an medical record by ID.
     */
    public void deleteMedicalRecord(int id) {
        medicalRecords.removeIf(medicalRecord -> medicalRecord.getId() == id);
        logger.info("Medical Record with ID {} deleted", id);
    }
    
    /**
     * Gives the ID for the next medical record.
     */
    public int getNextMedicalRecordId() {
        int maxMedicalRecordId = Integer.MIN_VALUE;
        
        for (MedicalRecord medicalRecord : medicalRecords) {
            int medicalRecordId = medicalRecord.getId();
            if (medicalRecordId > maxMedicalRecordId) {
                maxMedicalRecordId = medicalRecordId;
            }
        }
        return maxMedicalRecordId + 1;
    }
}
