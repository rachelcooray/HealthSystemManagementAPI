package com.mycompany.healthsystemapi.resources;

// Import required classes and libraries 
import com.mycompany.healthsystemapi.model.Person;
import com.mycompany.healthsystemapi.dao.PersonDAO;
import com.mycompany.healthsystemapi.exception.ResourceNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;

/**
 * Resource class for managing persons in the health system.
 * Provides endpoints for CRUD operations on persons.
 * 
 * @author rachelcooray
 */
@Path("/persons")
public class PersonResource {

    private PersonDAO personDAO = new PersonDAO();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    /**
     * Retrieves all persons by returning a list.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
        LOGGER.info("Getting all persons");
        try {
            return personDAO.getAllPersons();
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting all persons", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
    
    /**
     * Retrieves an person by its ID.
     * It returns the person with the specified ID, and throws ResourceNotFoundException if the person with the given ID is not found.
     */
    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(@PathParam("personId") int personId) {
        LOGGER.info("Getting person by ID: {}", personId);
        try {
            Person person = personDAO.getPersonById(personId);
            if (person == null) {
                LOGGER.warn("Person with ID {} not found", personId);
                throw new ResourceNotFoundException("Person with ID " + personId + " not found");
            }
            return person;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while getting person with ID " + personId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Adds a new person.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        LOGGER.info("Adding new person: {}", person);
        try {
            personDAO.addPerson(person);
            return Response.status(Response.Status.CREATED)
                    .entity("Person added.")
                    .build();
        } catch (BadRequestException e) {
            throw e; 
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding new person", e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Updates an existing person.
     * It returns the HTTP response indicating success or failure of the operation, and throws a ResourceNotFoundException if the person with the given ID is not found.
     */
    @PUT
    @Path("/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("personId") int personId, Person updatedPerson) {
        LOGGER.info("Updating person with ID {}: {}", personId, updatedPerson);
        try {
            Person existingPerson = personDAO.getPersonById(personId);
            if (existingPerson == null) {
                LOGGER.warn("Person with ID {} not found", personId);
                throw new ResourceNotFoundException("Person with ID " + personId + " not found");
            }
            updatedPerson.setId(personId);
            personDAO.updatePerson(updatedPerson);
            return Response.status(Response.Status.CREATED)
                    .entity("Person updated.")
                    .build();
        } catch (NotAllowedException e) {
            throw e; 
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating person with ID " + personId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }

    /**
     * Deletes a person by its ID.
     * It returns the HTTP response indicating success or failure of the operation.
     */
    @DELETE
    @Path("/{personId}")
    public Response deletePerson(@PathParam("personId") int personId) {
        LOGGER.info("Deleting person with ID: {}", personId);
        try {
            personDAO.deletePerson(personId);
            return Response.status(Response.Status.CREATED)
                    .entity("Person deleted.")
                    .build();
        } catch (NotAllowedException e) {
            throw e; 
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting person with ID " + personId, e);
            throw new InternalServerErrorException("Internal server error occurred");
        }
    }
}
