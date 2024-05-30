package com.mycompany.healthsystemapi.resources;

// Import required classes and libraries 
import com.mycompany.healthsystemapi.dao.DoctorDAO;
import com.mycompany.healthsystemapi.model.Doctor;
import com.mycompany.healthsystemapi.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 * Resource class for managing doctors in the health system.
 * Provides endpoints for CRUD operations on doctors.
 * 
 * @author rachelcooray
 */
@Path("/doctors")
public class DoctorResource {

    private DoctorDAO doctorDAO = new DoctorDAO();
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorResource.class);

    /**
     * Retrieves all doctors by returning a list.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctors() {
        LOGGER.info("Getting all doctors");
        try {
            return doctorDAO.getAllDoctors();
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting all doctors", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Retrieves a doctor by its ID.
     * It returns the doctor with the specified ID, and throws ResourceNotFoundException if the doctor with the given ID is not found.
     */
    @GET
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor getDoctorById(@PathParam("doctorId") int doctorId) {
        LOGGER.info("Getting doctor by ID: {}", doctorId);
        try {
            Doctor doctor = doctorDAO.getDoctorById(doctorId);
            if (doctor == null) {
                LOGGER.warn("Doctor with ID {} not found", doctorId);
                throw new ResourceNotFoundException("Doctor with ID " + doctorId + " not found");
            }
            return doctor;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting doctor with ID " + doctorId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Adds a new doctor.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        LOGGER.info("Adding new doctor: {}", doctor);
        try {
            doctorDAO.addDoctor(doctor);
            return Response.status(Response.Status.CREATED)
                    .entity("Doctor added.")
                    .build();
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding new doctor", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Updates an existing doctor.
     * It returns the HTTP response indicating success or failure of the operation, and throws a ResourceNotFoundException if the doctor with the given ID is not found.
     */
    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("doctorId") int doctorId, Doctor updatedDoctor) {
        LOGGER.info("Updating doctor with ID {}: {}", doctorId, updatedDoctor);
        try {
            Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);
            if (existingDoctor == null) {
                LOGGER.warn("Doctor with ID {} not found", doctorId);
                throw new ResourceNotFoundException("Doctor with ID " + doctorId + " not found");
            }
            updatedDoctor.setId(doctorId);
            doctorDAO.updateDoctor(updatedDoctor);
            return Response.status(Response.Status.CREATED)
                    .entity("Doctor updated.")
                    .build();
        } catch (NotAllowedException  e) {
            throw e;
        } catch(ResourceNotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            LOGGER.error("Error occurred while updating doctor with ID " + doctorId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Deletes a doctor by its ID.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @DELETE
    @Path("/{doctorId}")
    public Response deleteDoctor(@PathParam("doctorId") int doctorId) {
        LOGGER.info("Deleting doctor with ID: {}", doctorId);
        try {
            doctorDAO.deleteDoctor(doctorId);
            return Response.status(Response.Status.CREATED)
                    .entity("Doctor deleted.")
                    .build();
        } catch (NotAllowedException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting doctor with ID " + doctorId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
}
