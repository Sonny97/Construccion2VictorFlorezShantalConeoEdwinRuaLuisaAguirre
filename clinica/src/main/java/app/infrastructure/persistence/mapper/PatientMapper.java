package app.infrastructure.persistence.mapper;

import app.domain.model.Patient;
import app.infrastructure.persistence.entities.PatientEntity;

public class PatientMapper {
    
    public static PatientEntity toEntity(Patient patient) {
        if (patient == null) return null;
        
        PatientEntity entity = new PatientEntity();
        entity.setId(patient.getId());
        entity.setFirstName(patient.getFirstName());
        entity.setLastName(patient.getLastName());
        entity.setDocumentId(patient.getDocumentId());
        entity.setBirthDate(patient.getBirthDate());
        entity.setGender(patient.getGender());
        entity.setAddress(patient.getAddress());
        entity.setPhoneNumber(patient.getPhoneNumber());
        entity.setEmergencyContact(patient.getEmergencyContact());
        entity.setAllergies(patient.getAllergies());
        entity.setMedicalConditions(patient.getMedicalConditions());
        
        return entity;
    }
    
    public static Patient toDomain(PatientEntity entity) {
        if (entity == null) return null;
        
        Patient patient = new Patient();
        patient.setId(entity.getId());
        patient.setFirstName(entity.getFirstName());
        patient.setLastName(entity.getLastName());
        patient.setDocumentId(entity.getDocumentId());
        patient.setBirthDate(entity.getBirthDate());
        patient.setGender(entity.getGender());
        patient.setAddress(entity.getAddress());
        patient.setPhoneNumber(entity.getPhoneNumber());
        patient.setEmergencyContact(entity.getEmergencyContact());
        patient.setAllergies(entity.getAllergies());
        patient.setMedicalConditions(entity.getMedicalConditions());
        
        return patient;
    }
}