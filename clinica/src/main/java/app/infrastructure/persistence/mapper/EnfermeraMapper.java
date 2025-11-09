package app.infrastructure.persistence.mapper;

import app.infrastructure.persistence.entities.EnfermeraEntity;
import app.domain.model.Patient;

public class EnfermeraMapper {

    // Entity -> Domain
    public Patient toDomain(EnfermeraEntity entity) {
        if (entity == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setId(entity.getId());
        patient.setFirstName(entity.getFirstName());
        patient.setLastName(entity.getLastName());
        patient.setDocumentId(entity.getDocumentId());
        patient.setBirthDate(entity.getBirthDate());
        patient.setGender(entity.getGender());
        patient.setAddress(entity.getAddress());
        patient.setPhoneNumber(entity.getPhoneNumber());
        
        return patient;
    }

    
    public EnfermeraEntity toEntity(Patient patient) {
        if (patient == null) {
            return null;
        }
        EnfermeraEntity entity = new EnfermeraEntity();
        entity.setId(patient.getId());
        entity.setFirstName(patient.getFirstName());
        entity.setLastName(patient.getLastName());
        entity.setDocumentId(patient.getDocumentId());
        entity.setBirthDate(patient.getBirthDate());
        entity.setGender(patient.getGender());
        entity.setAddress(patient.getAddress());
        entity.setPhoneNumber(patient.getPhoneNumber());
        return entity;
    }
}
