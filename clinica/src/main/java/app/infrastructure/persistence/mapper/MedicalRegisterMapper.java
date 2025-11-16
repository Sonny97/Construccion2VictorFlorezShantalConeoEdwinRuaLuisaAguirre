package app.infrastructure.persistence.mapper;

import app.domain.model.MedicalRegister;
import app.infrastructure.persistence.entities.MedicalRegisterEntity;

public class MedicalRegisterMapper {
    
    public static MedicalRegisterEntity toEntity(MedicalRegister medicalRegister) {
        if (medicalRegister == null) return null;
        
        MedicalRegisterEntity entity = new MedicalRegisterEntity();
        entity.setId(medicalRegister.getId());
        entity.setPatientId(medicalRegister.getPatientId());
        entity.setMedicId(medicalRegister.getMedicId());
        entity.setRegisterDate(medicalRegister.getRegisterDate());
        entity.setInquiryReason(medicalRegister.getInquiryReason());
        entity.setSymptoms(medicalRegister.getSymptoms());
        entity.setPhysicalExam(medicalRegister.getPhysicalExam());
        entity.setDiagnosis(medicalRegister.getDiagnosis());
        entity.setTreatment(medicalRegister.getTreatment());
        entity.setPrescriptions(medicalRegister.getPrescriptions());
        entity.setObservations(medicalRegister.getObservations());
        
        return entity;
    }
    
    public static MedicalRegister toDomain(MedicalRegisterEntity entity) {
        if (entity == null) return null;
        
        MedicalRegister medicalRegister = new MedicalRegister();
        medicalRegister.setId(entity.getId());
        medicalRegister.setPatientId(entity.getPatientId());
        medicalRegister.setMedicId(entity.getMedicId());
        medicalRegister.setRegisterDate(entity.getRegisterDate());
        medicalRegister.setInquiryReason(entity.getInquiryReason());
        medicalRegister.setSymptoms(entity.getSymptoms());
        medicalRegister.setPhysicalExam(entity.getPhysicalExam());
        medicalRegister.setDiagnosis(entity.getDiagnosis());
        medicalRegister.setTreatment(entity.getTreatment());
        medicalRegister.setPrescriptions(entity.getPrescriptions());
        medicalRegister.setObservations(entity.getObservations());
        
        return medicalRegister;
    }
}
