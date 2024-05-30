package com.mycompany.healthsystemapi.dao;

// Import required classes and libraries
import com.mycompany.healthsystemapi.model.Billing;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Access Object (DAO) for managing billings in the health system.
 * Provides methods for CRUD operations on billings.
 * 
 * @author rachelcooray
 */
public class BillingDAO {
    private static List<Billing> billings = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(BillingDAO.class); // For logging messages
    
    // Initializing some demo billings
    static {
        billings.add(new Billing(1, 1, 100.0, "Paid", "2024-04-23", "2024-04-23", 0.0));
        billings.add(new Billing(2, 2, 150.0, "Pending", "2024-04-23", "2024-04-23", 150.0)); 
    }
    
    /**
     * Retrieves all billings by returning a list.
     */
    public List<Billing> getAllBillings() {
        try {
            return billings;
        } catch (Exception ex) {
            logger.error("Error occurred while retrieving all billings: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve billings.", ex);
        }
    }

    /**
     * Retrieves the billing with the specified ID.
     */
    public Billing getBillingById(int id) {
        for (Billing billing : billings) {
            if (billing.getId() == id) {
                return billing;
            }
        }
        return null;
    }
    
    /**
     * Adds a new billing to the list of billings.
     */
    public void addBilling(Billing billing) {
        int newBillingId = getNextBillingId();
        billing.setId(newBillingId);
        billings.add(billing);
        logger.info("Billing added: {}", billing);
    }

    /**
     * Updates an existing billing.
     */
    public void updateBilling(Billing updatedBilling) {
        for (int i = 0; i < billings.size(); i++) {
            Billing billing = billings.get(i);
            if (billing.getId() == updatedBilling.getId()) {
                billings.set(i, updatedBilling);
                logger.info("Billing updated: {}", updatedBilling);
                return;
            }
        }
    }

    /**
     * Deletes a billing by ID.
     */
    public void deleteBilling(int id) {
        billings.removeIf(billing -> billing.getId() == id);
        logger.info("Billing with ID {} deleted", id);
    }
    
    /**
     * Gives the ID for the next billing.
     */
    public int getNextBillingId() {
        int maxBillingId = Integer.MIN_VALUE;
        
        for (Billing billing : billings) {
            int billingId = billing.getId();
            if (billingId > maxBillingId) {
                maxBillingId = billingId;
            }
        }
        return maxBillingId + 1;
    }
}