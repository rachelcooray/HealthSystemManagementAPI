package com.mycompany.healthsystemapi.resources;

// Import required classes and libraries 
import com.mycompany.healthsystemapi.dao.PatientDAO;
import com.mycompany.healthsystemapi.model.Patient;
import com.mycompany.healthsystemapi.exception.ResourceNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;

/**
 * Resource class for managing appointments in the health system.
 * Provides endpoints for CRUD operations on appointments.
 * 
 * @author rachelcooray
 */
@Path("/patients")
public class PatientResource {

    private PatientDAO patientDAO = new PatientDAO();
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientResource.class);

    /**
     * Retrieves all patients by returning a list.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getAllPatients() {
        LOGGER.info("Getting all patients");
        try {
            return patientDAO.getAllPatients();
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting all patients", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Retrieves a patient by its ID.
     * It returns the patient with the specified ID, and throws ResourceNotFoundException if the v with the given ID is not found.
     */
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("patientId") int patientId) {
        LOGGER.info("Getting patient by ID: {}", patientId);
        try {
            Patient patient = patientDAO.getPatientById(patientId);
            if (patient == null) {
                LOGGER.warn("Patient with ID {} not found", patientId);
                throw new ResourceNotFoundException("Patient with ID " + patientId + " not found");
            }
            return patient;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting patient with ID " + patientId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Adds a new patient.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        LOGGER.info("Adding new patient: {}", patient);
        try {
            patientDAO.addPatient(patient);
            return Response.status(Response.Status.CREATED)
                    .entity("Patient added.")
                    .build();
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding new patient", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Updates an existing patient.
     * It returns the HTTP response indicating success or failure of the operation, and throws a ResourceNotFoundException if the patient with the given ID is not found.
     */
    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("patientId") int patientId, Patient updatedPatient) {
        LOGGER.info("Updating patient with ID {}: {}", patientId, updatedPatient);
        try {
            Patient existingPatient = patientDAO.getPatientById(patientId);
            if (existingPatient == null) {
                LOGGER.warn("Patient with ID {} not found", patientId);
                throw new ResourceNotFoundException("Patient with ID " + patientId + " not found");
            }
            updatedPatient.setId(patientId);
            patientDAO.updatePatient(updatedPatient);
            return Response.status(Response.Status.CREATED)
                    .entity("Patient updated.")
                    .build();
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (NotAllowedException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating patient with ID " + patientId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Deletes an patient by its ID.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @DELETE
    @Path("/{patientId}")
    public Response deletePatient(@PathParam("patientId") int patientId) {
        LOGGER.info("Deleting patient with ID: {}", patientId);
        try {
            patientDAO.deletePatient(patientId);
            return Response.status(Response.Status.CREATED)
                    .entity("Patient deleted.")
                    .build();
        } catch (NotAllowedException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting patient with ID " + patientId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
}