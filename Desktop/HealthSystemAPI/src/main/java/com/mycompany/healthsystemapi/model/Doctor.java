package com.mycompany.healthsystemapi.model;

/**
 * Represents a doctor in the health system.
 * 
 * A doctor's object includes information such as ID, name, contact info, address and specialization.
 * It extends the Person class.
 * 
 * @author rachelcooray
 */
public class Doctor extends Person{
    // Attributes of this class
    private String specialization;

    /**
     * Constructs a Doctor object with the specified ID and other attributes.
     */
    public Doctor(int id, String name, String contactInfo, String address, String specialization) {
        super(id, name, contactInfo, address);
        this.specialization = specialization;
    }

    /**
     * Default constructor for the class.
     */
    public Doctor() {
        super(); // Call the default constructor of the superclass (Person)
    }
    
    // Getters and setters of each attribute
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
      
}
