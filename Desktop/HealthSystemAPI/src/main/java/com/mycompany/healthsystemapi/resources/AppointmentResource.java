package com.mycompany.healthsystemapi.resources;

// Import required classes and libraries 
import com.mycompany.healthsystemapi.dao.AppointmentDAO;
import com.mycompany.healthsystemapi.model.Appointment;
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
 * Resource class for managing appointments in the health system.
 * Provides endpoints for CRUD operations on appointments.
 * 
 * @author rachelcooray
 */
@Path("/appointments")
public class AppointmentResource {

    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentResource.class);

    /**
     * Retrieves all appointments by returning a list.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointments() {
        LOGGER.info("Getting all appointments");
        try {
            return appointmentDAO.getAllAppointments();
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting all appointments", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Retrieves an appointment by its ID.
     * It returns the appointment with the specified ID, and throws ResourceNotFoundException if the appointment with the given ID is not found.
     */
    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Appointment getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        LOGGER.info("Getting appointment by ID: {}", appointmentId);
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment == null) {
                LOGGER.warn("Appointment with ID {} not found", appointmentId);
                throw new ResourceNotFoundException("Appointment with ID " + appointmentId + " not found");
            }
            return appointment;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting appointment with ID " + appointmentId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
    
    /**
     * Retrieves appointments associated with a specific doctor.
     * It returns a list of appointments associated with the specified doctor.
     */
    @GET
    @Path("/doctor/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAppointmentsByDoctorId(@PathParam("doctorId") int doctorId) {
        LOGGER.info("Getting appointments by doctor ID: {}", doctorId);
        try {
            return appointmentDAO.getAppointmentsByDoctorId(doctorId);
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting appointments for doctor with ID " + doctorId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Adds a new appointment.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        LOGGER.info("Adding new appointment: {}", appointment);
        try {
            appointmentDAO.addAppointment(appointment);
            return Response.status(Response.Status.CREATED)
                    .entity("Appointment added.")
                    .build();
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding new appointment", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Updates an existing appointment.
     * It returns the HTTP response indicating success or failure of the operation, and throws a ResourceNotFoundException if the appointment with the given ID is not found.
     */
    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
        LOGGER.info("Updating appointment with ID {}: {}", appointmentId, updatedAppointment);
        try {
            Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);
            if (existingAppointment == null) {
                LOGGER.warn("Appointment with ID {} not found", appointmentId);
                throw new ResourceNotFoundException("Appointment with ID " + appointmentId + " not found");
            }
            updatedAppointment.setId(appointmentId);
            appointmentDAO.updateAppointment(updatedAppointment);
            return Response.status(Response.Status.CREATED)
                    .entity("Appointment updated.")
                    .build();
        } catch (NotAllowedException e) {
            throw e;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating appointment with ID " + appointmentId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Deletes an appointment by its ID.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        LOGGER.info("Deleting appointment with ID: {}", appointmentId);
        try {
            appointmentDAO.deleteAppointment(appointmentId);
            return Response.status(Response.Status.CREATED)
                    .entity("Appointment deleted.")
                    .build();
        } catch (NotAllowedException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting appointment with ID " + appointmentId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
}
