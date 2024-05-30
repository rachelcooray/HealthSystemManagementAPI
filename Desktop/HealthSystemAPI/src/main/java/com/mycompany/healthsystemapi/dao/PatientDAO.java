package com.mycompany.healthsystemapi.dao;

// Import required classes and libraries
import com.mycompany.healthsystemapi.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Access Object (DAO) for managing patients in the health system.
 * Provides methods for CRUD operations on patients.
 * 
 * @author rachelcooray
 */
public class PatientDAO {
    private static List<Patient> patients = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(PatientDAO.class); // For logging messages
    
    // Initializing some demo patients
    static {
        patients.add(new Patient("Chronic allergies", "Stable", 1, "Taylor Lautner", "0772563564", "1A, Main Street, Galle"));
        patients.add(new Patient("Diabetes type 2", "Under control", 2, "Tim Collins", "0772564824", "5A, Main Street, Galle")); 
    }
    
    /**
     * Retrieves all patients by returning a list.
     */
    public List<Patient> getAllPatients() {
        try {
            return patients;
        } catch (Exception ex) {
            logger.error("Error occurred while retrieving all patients: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve patients.", ex);
        }
    }

    /**
     * Retrieves the patient with the specified ID.
     */
    public Patient getPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }
    
    /**
     * Adds a new patient to the list of patients.
     */
    public void addPatient(Patient patient) {
        int newPatientId = getNextPatientId();
        patient.setId(newPatientId);
        patients.add(patient);
        logger.info("Patient added: {}", patient);
    }

    /**
     * Updates an existing patient.
     */
    public void updatePatient(Patient updatedPatient) {
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            if (patient.getId() == updatedPatient.getId()) {
                patients.set(i, updatedPatient);
                logger.info("Patient updated: {}", updatedPatient);
                return;
            }
        }
    }

    /**
     * Deletes an patient by ID.
     */
    public void deletePatient(int id) {
        patients.removeIf(patient -> patient.getId() == id);
        logger.info("Patient with ID {} deleted", id);
    }
    
    /**
     * Gives the ID for the next patient.
     */
    public int getNextPatientId() {
        int maxPatientId = Integer.MIN_VALUE;
        
        for (Patient patient : patients) {
            int patientId = patient.getId();
            if (patientId > maxPatientId) {
                maxPatientId = patientId;
            }
        }
        return maxPatientId + 1;
    }
}
