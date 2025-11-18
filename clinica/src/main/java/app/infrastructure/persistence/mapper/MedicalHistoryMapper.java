package app.infrastructure.persistence.mapper;

import app.domain.model.MedicalHistory;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;

/**
 * Mapper para convertir entre entidades JPA y modelos de dominio de MedicalHistory
 */
public class MedicalHistoryMapper {
    
    /**
     * Convierte un modelo de dominio a una entidad JPA
     */
    public static MedicalHistoryEntity toEntity(MedicalHistory medicalHistory) {
        if (medicalHistory == null) {
            return null;
        }
        
        MedicalHistoryEntity entity = new MedicalHistoryEntity();
        entity.setId(medicalHistory.getId());
        entity.setPatientId(medicalHistory.getPatientId());
        entity.setPatientName(medicalHistory.getPatientName());
        entity.setCreationDate(medicalHistory.getCreationDate());
        entity.setLastUpdateDate(medicalHistory.getLastUpdateDate());
        entity.setBloodType(medicalHistory.getBloodType());
        entity.setAllergies(medicalHistory.getAllergies());
        entity.setChronicDiseases(medicalHistory.getChronicDiseases());
        entity.setFamilyHistory(medicalHistory.getFamilyHistory());
        entity.setSurgicalHistory(medicalHistory.getSurgicalHistory());
        entity.setObservations(medicalHistory.getObservations());
        
        return entity;
    }
    
    /**
     * Convierte una entidad JPA a un modelo de dominio
     */
    public static MedicalHistory toDomain(MedicalHistoryEntity entity) {
        if (entity == null) {
            return null;
        }
        
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setId(entity.getId());
        medicalHistory.setPatientId(entity.getPatientId());
        medicalHistory.setPatientName(entity.getPatientName());
        medicalHistory.setCreationDate(entity.getCreationDate());
        medicalHistory.setLastUpdateDate(entity.getLastUpdateDate());
        medicalHistory.setBloodType(entity.getBloodType());
        medicalHistory.setAllergies(entity.getAllergies());
        medicalHistory.setChronicDiseases(entity.getChronicDiseases());
        medicalHistory.setFamilyHistory(entity.getFamilyHistory());
        medicalHistory.setSurgicalHistory(entity.getSurgicalHistory());
        medicalHistory.setObservations(entity.getObservations());
        
        return medicalHistory;
    }
}
