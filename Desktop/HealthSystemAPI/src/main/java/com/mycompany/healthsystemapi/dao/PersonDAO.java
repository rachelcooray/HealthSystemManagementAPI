package com.mycompany.healthsystemapi.dao;

// Import required classes and libraries
import com.mycompany.healthsystemapi.model.Person;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Access Object (DAO) for managing persons in the health system.
 * Provides methods for CRUD operations on persons.
 * 
 * @author rachelcooray
 */
public class PersonDAO {
    private static List<Person> persons = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(PersonDAO.class); // For logging messages
    
    // Initializing some demo persons
    static {
        persons.add(new Person(1, "Taylor Lautner", "0772563564", "1A, Main Street, Galle"));
        persons.add(new Person(2, "Tim Collins", "0772564824", "5A, Main Street, Galle"));
    }
    
    /**
     * Retrieves all persons by returning a list.
     */
    public List<Person> getAllPersons() {
        try {
            return persons;
        } catch (Exception ex) {
            logger.error("Error occurred while retrieving all persons: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve persons.", ex);
        }
    }

    /**
     * Retrieves the person with the specified ID.
     */
    public Person getPersonById(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }
    
    /**
     * Adds a new person to the list of persons.
     */
    public void addPerson(Person person) {
        int newPersonId = getNextPersonId();
        person.setId(newPersonId);
        persons.add(person);
        logger.info("Person added: {}", person);
    }

    /**
     * Updates an existing person.
     */
    public void updatePerson(Person updatedPerson) {
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getId() == updatedPerson.getId()) {
                persons.set(i, updatedPerson);
                logger.info("Person updated: {}", updatedPerson);
                return;
            }
        }
    }

    /**
     * Deletes an person by ID.
     */
    public void deletePerson(int id) {
        persons.removeIf(person -> person.getId() == id);
        logger.info("Person with ID {} deleted", id);
    }
    
    /**
     * Gives the ID for the next person.
     */
    public int getNextPersonId() {
        int maxPersonId = Integer.MIN_VALUE;
        
        for (Person person : persons) {
            int userId = person.getId();
            if (userId > maxPersonId) {
                maxPersonId = userId;
            }
        }
        return maxPersonId + 1;
    }
}
