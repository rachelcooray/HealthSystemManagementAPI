package com.mycompany.healthsystemapi.resources;

// Import required classes and libraries 
import com.mycompany.healthsystemapi.dao.MedicalRecordDAO;
import com.mycompany.healthsystemapi.model.MedicalRecord;
import com.mycompany.healthsystemapi.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;

/**
 * Resource class for managing medical records in the health system.
 * Provides endpoints for CRUD operations on medical records.
 * 
 * @author rachelcooray
 */
@Path("/medical-records")
public class MedicalRecordResource {

    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordResource.class);

    /**
     * Retrieves all medical records by returning a list.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getAllMedicalRecords() {
        LOGGER.info("Getting all medical records");
        try {
            return medicalRecordDAO.getAllMedicalRecords();
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting all medical records", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Retrieves a medical record by its ID.
     * It returns the medical record with the specified ID, and throws ResourceNotFoundException if the medical record with the given ID is not found.
     */
    @GET
    @Path("/{medicalRecordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MedicalRecord getMedicalRecordById(@PathParam("medicalRecordId") int medicalRecordId) {
        LOGGER.info("Getting medical record by ID: {}", medicalRecordId);
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(medicalRecordId);
            if (medicalRecord == null) {
                LOGGER.warn("Medical record with ID {} not found", medicalRecordId);
                throw new ResourceNotFoundException("Medical record with ID " + medicalRecordId + " not found");
            }
            return medicalRecord;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting medical record with ID " + medicalRecordId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
    
    /**
     * Retrieves medical records associated with a specific patient.
     * It returns a list of medical records associated with the specified patient.
     */
    @GET
    @Path("/patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getMedicalRecordsByPatientId(@PathParam("patientId") int patientId) {
        LOGGER.info("Getting medical records by patient ID: {}", patientId);
        try {
            return medicalRecordDAO.getMedicalRecordsByPatientId(patientId);
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting medical records for patient with ID " + patientId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Adds a new medical record.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        LOGGER.info("Adding new medical record: {}", medicalRecord);
        try {
            medicalRecordDAO.addMedicalRecord(medicalRecord);
            return Response.status(Response.Status.CREATED)
                    .entity("Medical record added.")
                    .build();
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding new medical record", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Updates an existing medical record.
     * It returns the HTTP response indicating success or failure of the operation, and throws a ResourceNotFoundException if the medical record with the given ID is not found.
     */
    @PUT
    @Path("/{medicalRecordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("medicalRecordId") int medicalRecordId, MedicalRecord updatedMedicalRecord) {
        LOGGER.info("Updating medical record with ID {}: {}", medicalRecordId, updatedMedicalRecord);
        try {
            MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecordById(medicalRecordId);
            if (existingMedicalRecord == null) {
                LOGGER.warn("Medical record with ID {} not found", medicalRecordId);
                throw new ResourceNotFoundException("Medical record with ID " + medicalRecordId + " not found");
            }
            updatedMedicalRecord.setId(medicalRecordId);
            medicalRecordDAO.updateMedicalRecord(updatedMedicalRecord);
            return Response.status(Response.Status.CREATED)
                    .entity("Medical record updated.")
                    .build();
        } catch (NotAllowedException  e) {
            throw e;
        } catch(ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating medical record with ID " + medicalRecordId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Deletes a medical record by its ID.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @DELETE
    @Path("/{medicalRecordId}")
    public Response deleteMedicalRecord(@PathParam("medicalRecordId") int medicalRecordId) {
        LOGGER.info("Deleting medical record with ID: {}", medicalRecordId);
        try {
            medicalRecordDAO.deleteMedicalRecord(medicalRecordId);
            return Response.status(Response.Status.CREATED)
                    .entity("Medical record deleted.")
                    .build();
        } catch (NotAllowedException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting medical record with ID " + medicalRecordId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
}