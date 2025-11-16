package app.domain.services;

import app.domain.model.EmergencyContact;
import app.domain.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class EmergencyContactService {

    public EmergencyContact createEmergencyContact(EmergencyContact contact, Patient patient) {
        validateEmergencyContact(contact);
        contact.setPatient(patient);
        return contact;
    }

    public EmergencyContact updateEmergencyContact(EmergencyContact existingContact, EmergencyContact updatedContact) {
        if (updatedContact.getFirstName() != null) {
            existingContact.setFirstName(updatedContact.getFirstName());
        }
        if (updatedContact.getLastName() != null) {
            existingContact.setLastName(updatedContact.getLastName());
        }
        if (updatedContact.getRelationship() != null) {
            existingContact.setRelationship(updatedContact.getRelationship());
        }
        if (updatedContact.getPhoneNumber() != null) {
            validatePhoneNumber(updatedContact.getPhoneNumber());
            existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
        }
        return existingContact;
    }

    private void validateEmergencyContact(EmergencyContact contact) {
        if (contact.getFirstName() == null || contact.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("Emergency contact first name is required");
        }
        if (contact.getLastName() == null || contact.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Emergency contact last name is required");
        }
        if (contact.getRelationship() == null || contact.getRelationship().trim().isEmpty()) {
            throw new IllegalArgumentException("Emergency contact relationship is required");
        }
        validatePhoneNumber(contact.getPhoneNumber());
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Emergency contact phone number is required");
        }
        if (!phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits");
        }
    }
}