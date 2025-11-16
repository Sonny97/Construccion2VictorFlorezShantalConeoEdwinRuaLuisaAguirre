package app.adapter.rest.mapper;

import app.adapter.rest.request.EmergencyContactRequest;
import app.adapter.rest.response.EmergencyContactResponse;
import app.domain.model.EmergencyContact;
import app.infrastructure.persistence.entities.EmergencyContactEntity;
import org.springframework.stereotype.Component;

@Component
public class EmergencyContactRestMapper {

    public EmergencyContact toDomain(EmergencyContactRequest request) {
        if (request == null) return null;
        
        EmergencyContact contact = new EmergencyContact();
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setRelationship(request.getRelationship());
        contact.setPhoneNumber(request.getPhoneNumber());
        return contact;
    }

    public EmergencyContactResponse toResponse(EmergencyContact contact) {
        if (contact == null) return null;
        
        EmergencyContactResponse response = new EmergencyContactResponse();
        response.setId(contact.getId());
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setRelationship(contact.getRelationship());
        response.setPhoneNumber(contact.getPhoneNumber());
        if (contact.getPatient() != null) {
            response.setPatientId(contact.getPatient().getId());
        }
        return response;
    }

    public EmergencyContactEntity toEntity(EmergencyContact contact) {
        if (contact == null) return null;
        
        EmergencyContactEntity entity = new EmergencyContactEntity();
        entity.setId(contact.getId());
        entity.setFirstName(contact.getFirstName());
        entity.setLastName(contact.getLastName());
        entity.setRelationship(contact.getRelationship());
        entity.setPhoneNumber(contact.getPhoneNumber());
        return entity;
    }

    public EmergencyContact toDomain(EmergencyContactEntity entity) {
        if (entity == null) return null;
        
        EmergencyContact contact = new EmergencyContact();
        contact.setId(entity.getId());
        contact.setFirstName(entity.getFirstName());
        contact.setLastName(entity.getLastName());
        contact.setRelationship(entity.getRelationship());
        contact.setPhoneNumber(entity.getPhoneNumber());
        return contact;
    }
}