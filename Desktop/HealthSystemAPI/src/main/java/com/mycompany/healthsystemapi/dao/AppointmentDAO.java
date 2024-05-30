package com.mycompany.healthsystemapi.dao;

// Import required classes and libraries
import com.mycompany.healthsystemapi.model.Appointment;
import com.mycompany.healthsystemapi.model.Doctor;
import com.mycompany.healthsystemapi.model.Patient;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Access Object (DAO) for managing appointments in the health system.
 * Provides methods for CRUD operations on appointments.
 * 
 * @author rachelcooray
 */
public class AppointmentDAO {
    private static List<Appointment> appointments = new ArrayList<>(); // Store list of appointments
    private static final Logger logger = LoggerFactory.getLogger(AppointmentDAO.class); // For logging messages
    
    // Initializing some demo appointments
    static {
        Patient patient1 = new Patient("Chronic allergies", "Stable", 1, "Anne Smith", "0772563564", "1A, Main Street, Galle");
        Doctor doctor1 = new Doctor(1, "Dr. John Legend", "0712345678", "123, Main Street, Colombo", "Cardiologist");
        appointments.add(new Appointment(1, "2024-04-23", patient1, doctor1));

        Patient patient2 = new Patient("Diabetes type 2", "Under control", 2, "Mary Lee", "0772564824", "5A, Main Street, Galle");
        Doctor doctor2 = new Doctor(2, "Dr. Olivia Rodrigo", "0712345679", "456, Park Road, Kandy", "Physician");
        appointments.add(new Appointment(2, "2024-04-24", patient2, doctor2));
    }
    
    /**
     * Retrieves all appointments by returning a list.
     */
    public List<Appointment> getAllAppointments() {
        try {
            return appointments;
        } catch (Exception ex) {
            logger.error("Error occurred while retrieving all appointments: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve appointments.", ex);
        }
    }

    /**
     * Retrieves the appointment with the specified ID.
     */
    public Appointment getAppointmentById(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        return null;
    }

    /**
     * Retrieves appointments associated with a specific doctor.
     */
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().getId() == doctorId) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

    /**
     * Adds a new appointment to the list of appointments.
     */
    public void addAppointment(Appointment appointment) {
        int newAppointmentId = getNextAppointmentId();
        appointment.setId(newAppointmentId);
        appointments.add(appointment);
        logger.info("Appointment added: {}", appointment);
    }

    /**
     * Updates an existing appointment.
     */
    public void updateAppointment(Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getId() == updatedAppointment.getId()) {
                appointments.set(i, updatedAppointment);
                logger.info("Appointment updated: {}", updatedAppointment);
                return;
            }
        }
    }

    /**
     * Deletes an appointment by ID.
     */
    public void deleteAppointment(int id) {
        appointments.removeIf(appointment -> appointment.getId() == id);
        logger.info("Appointment with ID {} deleted", id);
    }

    /**
     * Gives the ID for the next appointment.
     */
    public int getNextAppointmentId() {
        int maxAppointmentId = Integer.MIN_VALUE;

        for (Appointment appointment : appointments) {
            int appointmentId = appointment.getId();
            if (appointmentId > maxAppointmentId) {
                maxAppointmentId = appointmentId;
            }
        }
        return maxAppointmentId + 1;
    }
}