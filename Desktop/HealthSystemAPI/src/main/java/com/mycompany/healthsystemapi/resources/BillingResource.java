package com.mycompany.healthsystemapi.resources;

// Import required classes and libraries 
import com.mycompany.healthsystemapi.dao.BillingDAO;
import com.mycompany.healthsystemapi.model.Billing;
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
 * Resource class for managing billings in the health system.
 * Provides endpoints for CRUD operations on billings.
 * 
 * @author rachelcooray
 */
@Path("/billings")
public class BillingResource {

    private BillingDAO billingDAO = new BillingDAO();

    private static final Logger LOGGER = LoggerFactory.getLogger(BillingResource.class);

    /**
     * Retrieves all billings by returning a list.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billing> getAllBillings() {
        LOGGER.info("Getting all billings");
        try {
            return billingDAO.getAllBillings();
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting all billings", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Retrieves a billing by its ID.
     * It returns the billing with the specified ID, and throws ResourceNotFoundException if the billing with the given ID is not found.
     */
    @GET
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Billing getBillingById(@PathParam("billingId") int billingId) {
        LOGGER.info("Getting billing by ID: {}", billingId);
        try {
            Billing billing = billingDAO.getBillingById(billingId);
            if (billing == null) {
                LOGGER.warn("Billing with ID {} not found", billingId);
                throw new ResourceNotFoundException("Billing with ID " + billingId + " not found");
            }
            return billing;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting billing with ID " + billingId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Adds a new billing.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        LOGGER.info("Adding new billing: {}", billing);
        try {
            billingDAO.addBilling(billing);
            return Response.status(Response.Status.CREATED)
                    .entity("Billing added.")
                    .build();
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding new billing", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Updates an existing billing.
     * It returns the HTTP response indicating success or failure of the operation, and throws a ResourceNotFoundException if the billing with the given ID is not found.
     */
    @PUT
    @Path("/{billingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("billingId") int billingId, Billing updatedBilling) {
        LOGGER.info("Updating billing with ID {}: {}", billingId, updatedBilling);
        try {
            Billing existingBilling = billingDAO.getBillingById(billingId);
            if (existingBilling == null) {
                LOGGER.warn("Billing with ID {} not found", billingId);
                throw new ResourceNotFoundException("Billing with ID " + billingId + " not found");
            }
            updatedBilling.setId(billingId);
            billingDAO.updateBilling(updatedBilling);
            return Response.status(Response.Status.CREATED)
                    .entity("Billing updated.")
                    .build();
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (NotAllowedException e){
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating billing with ID " + billingId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Deletes a billing by its ID.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @DELETE
    @Path("/{billingId}")
    public Response deleteBilling(@PathParam("billingId") int billingId) {
        LOGGER.info("Deleting billing with ID: {}", billingId);
        try {
            billingDAO.deleteBilling(billingId);
            return Response.status(Response.Status.CREATED)
                    .entity("Billing deleted.")
                    .build();
        } catch (NotAllowedException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting billing with ID " + billingId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
}
