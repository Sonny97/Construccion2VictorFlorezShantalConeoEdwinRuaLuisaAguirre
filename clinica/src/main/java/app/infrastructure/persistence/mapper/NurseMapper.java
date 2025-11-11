package app.infrastructure.persistence.mapper;

import app.infrastructure.persistence.entities.NurseEntity;
import app.domain.model.Patient;

public class NurseMapper {

    // Entity -> Domain
    public Patient toDomain(NurseEntity entity) {
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

    
    public NurseEntity toEntity(Patient patient) {
        if (patient == null) {
            return null;
        }
        NurseEntity entity = new NurseEntity();
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
