package com.mycompany.healthsystemapi.dao;

// Import required classes and libraries
import com.mycompany.healthsystemapi.model.Prescription;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Access Object (DAO) for managing prescriptions in the health system.
 * Provides methods for CRUD operations on prescriptions.
 * 
 * @author rachelcooray
 */
public class PrescriptionDAO {
    private static List<Prescription> prescriptions = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(PrescriptionDAO.class); // For logging messages
    
    // Initializing some demo prescriptions
    static {
        prescriptions.add(new Prescription(1, 1, "Paracetamol", "500mg", "Take twice daily", "7 days"));
        prescriptions.add(new Prescription(2, 2, "Aspirin", "325mg", "Take with food", "14 days"));
    }
    
    /**
     * Retrieves all prescriptions by returning a list.
     */
    public List<Prescription> getAllPrescriptions() {
        try {
            return prescriptions;
        } catch (Exception ex) {
            logger.error("Error occurred while retrieving all prescriptions: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve prescriptions.", ex);
        }
    }

    /**
     * Retrieves the prescription with the specified ID.
     */
    public Prescription getPrescriptionById(int id) {
        for (Prescription prescription : prescriptions) {
            if (prescription.getId() == id) {
                return prescription;
            }
        }
        return null;
    }
    
    /**
     * Adds a new prescription to the list of prescriptions.
     */
    public void addPrescription(Prescription prescription) {
        int newPrescriptionId = getNextPrescriptionId();
        prescription.setId(newPrescriptionId);
        prescriptions.add(prescription);
        logger.info("Prescription added: {}", prescription);
    }

    /**
     * Updates an existing prescription.
     */
    public void updatePrescription(Prescription updatedPrescription) {
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            if (prescription.getId() == updatedPrescription.getId()) {
                prescriptions.set(i, updatedPrescription);
                logger.info("Prescription updated: {}", updatedPrescription);
                return;
            }
        }
    }

    /**
     * Deletes an prescription by ID.
     */
    public void deletePrescription(int id) {
        prescriptions.removeIf(prescription -> prescription.getId() == id);
        logger.info("Prescription with ID {} deleted", id);
    }
    
    /**
     * Gives the ID for the next prescription.
     */
    public int getNextPrescriptionId() {
        int maxPrescriptionId = Integer.MIN_VALUE;
        
        for (Prescription prescription : prescriptions) {
            int prescriptionId = prescription.getId();
            if (prescriptionId > maxPrescriptionId) {
                maxPrescriptionId = prescriptionId;
            }
        }
        return maxPrescriptionId + 1;
    }
}
