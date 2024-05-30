package com.mycompany.healthsystemapi.dao;

// Import required classes and libraries
import com.mycompany.healthsystemapi.model.Doctor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Access Object (DAO) for managing doctors in the health system.
 * Provides methods for CRUD operations on doctors.
 * 
 * @author rachelcooray
 */
public class DoctorDAO {
    private static List<Doctor> doctors = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(DoctorDAO.class); // For logging messages
    
    // Initializing some demo doctors
    static {
        doctors.add(new Doctor(1, "Dr. John Legend", "0712345678", "123, Main Street, Colombo", "Cardiologist"));
        doctors.add(new Doctor(2, "Dr. Olivia Rodrigo", "0712345679", "456, Park Road, Kandy", "Physician"));
    }
    
    /**
     * Retrieves all doctors by returning a list.
     */
    public List<Doctor> getAllDoctors() {
        try {
            return doctors;
        } catch (Exception ex) {
            logger.error("Error occurred while retrieving all doctors: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve doctors.", ex);
        }
    }

    /**
     * Retrieves the doctor with the specified ID.
     */
    public Doctor getDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }
    
    /**
     * Adds a new doctor to the list of doctors.
     */
    public void addDoctor(Doctor doctor) {
        int newDoctorId = getNextDoctorId();
        doctor.setId(newDoctorId);
        doctors.add(doctor);
        logger.info("Doctor added: {}", doctor);
    }

    /**
     * Updates an existing doctor.
     */
    public void updateDoctor(Doctor updatedDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.getId() == updatedDoctor.getId()) {
                doctors.set(i, updatedDoctor);
                logger.info("Doctor updated: {}", updatedDoctor);
                return;
            }
        }
    }

    /**
     * Deletes an doctor by ID.
     */
    public void deleteDoctor(int id) {
        doctors.removeIf(doctor -> doctor.getId() == id);
        logger.info("Doctor with ID {} deleted", id);
    }
    
    /**
     * Gives the ID for the next doctor.
     */
    public int getNextDoctorId() {
        int maxDoctorId = Integer.MIN_VALUE;
        
        for (Doctor doctor : doctors) {
            int doctorId = doctor.getId();
            if (doctorId > maxDoctorId) {
                maxDoctorId = doctorId;
            }
        }
        return maxDoctorId + 1;
    }
}
