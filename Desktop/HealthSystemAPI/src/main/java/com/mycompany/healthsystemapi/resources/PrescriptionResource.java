package com.mycompany.healthsystemapi.resources;

// Import required classes and libraries 
import com.mycompany.healthsystemapi.dao.PrescriptionDAO;
import com.mycompany.healthsystemapi.model.Prescription;
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
 * Resource class for managing prescriptions in the health system.
 * Provides endpoints for CRUD operations on prescriptions.
 * 
 * @author rachelcooray
 */
@Path("/prescriptions")
public class PrescriptionResource {

    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionResource.class);

    /**
     * Retrieves all prescriptions by returning a list.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getAllPrescriptions() {
        LOGGER.info("Getting all prescriptions");
        try {
            return prescriptionDAO.getAllPrescriptions();
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting all prescriptions", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Retrieves an prescription by its ID.
     * It returns the prescription with the specified ID, and throws ResourceNotFoundException if the prescription with the given ID is not found.
     */
    @GET
    @Path("/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription getPrescriptionById(@PathParam("prescriptionId") int prescriptionId) {
        LOGGER.info("Getting prescription by ID: {}", prescriptionId);
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (prescription == null) {
                LOGGER.warn("Prescription with ID {} not found", prescriptionId);
                throw new ResourceNotFoundException("Prescription with ID " + prescriptionId + " not found");
            }
            return prescription;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting prescription with ID " + prescriptionId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Adds a new prescription.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        LOGGER.info("Adding new prescription: {}", prescription);
        try {
            prescriptionDAO.addPrescription(prescription);
            return Response.status(Response.Status.CREATED)
                    .entity("Prescription added.")
                    .build();
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding new prescription", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Updates an existing prescription.
     * It returns the HTTP response indicating success or failure of the operation, and throws a ResourceNotFoundException if the prescription with the given ID is not found.
     */
    @PUT
    @Path("/{prescriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("prescriptionId") int prescriptionId, Prescription updatedPrescription) {
        LOGGER.info("Updating prescription with ID {}: {}", prescriptionId, updatedPrescription);
        try {
            Prescription existingPrescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (existingPrescription == null) {
                LOGGER.warn("Prescription with ID {} not found", prescriptionId);
                throw new ResourceNotFoundException("Prescription with ID " + prescriptionId + " not found");
            }
            updatedPrescription.setId(prescriptionId);
            prescriptionDAO.updatePrescription(updatedPrescription);
            return Response.status(Response.Status.CREATED)
                    .entity("Prescription updated.")
                    .build();
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (NotAllowedException e){
            throw e;
        }
        catch (Exception e) {
            LOGGER.error("Error occurred while updating prescription with ID " + prescriptionId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Deletes a prescription by its ID.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @DELETE
    @Path("/{prescriptionId}")
    public Response deletePrescription(@PathParam("prescriptionId") int prescriptionId) {
        LOGGER.info("Deleting prescription with ID: {}", prescriptionId);
        try {
            prescriptionDAO.deletePrescription(prescriptionId);
            return Response.status(Response.Status.CREATED)
                    .entity("Prescription deleted.")
                    .build();
        } catch (NotAllowedException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting prescription with ID " + prescriptionId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
}