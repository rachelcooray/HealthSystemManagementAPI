package com.mycompany.healthsystemapi.model;

/**
 * Represents a person in the health system.
 * 
 * A person's object includes information such as ID, name, contact info and address.
 * 
 * @author rachelcooray
 */
public class Person {
    // Attributes of this class
    private int id;
    private String name;
    private String contactInfo;
    private String address;   

    /**
     * Default constructor for the class.
     */
    public Person() {
        
    }

    /**
     * Constructs a person object with the specified ID and other attributes.
     */
    public Person(int id, String name, String contactInfo, String address) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    // Getters and setters of each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
