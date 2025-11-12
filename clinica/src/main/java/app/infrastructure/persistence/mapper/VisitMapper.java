package app.infrastructure.persistence.mapper;

import app.domain.model.Visit;
import app.infrastructure.persistence.entities.VisitEntity;
import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.entities.PatientEntity;

public class VisitMapper {
    
    public static VisitEntity toEntity(Visit visit) {
        if (visit == null) return null;
        
        VisitEntity entity = new VisitEntity();
        // entity.setId((long) visit.getId());
        
        // Crear PatientEntity para la relación
        PatientEntity patient = new PatientEntity();
        patient.setId(visit.getPatientId());
        entity.setPatient(patient);
        
        // Crear UserEntity para la relación (enfermera)
        UserEntity nurse = new UserEntity();
        nurse.setId(visit.getNurseId());
        entity.setNurse(nurse);
        
        entity.setVisitDate(visit.getVisitDate());
        entity.setBloodPressure(visit.getBloodPressure());
        entity.setTemperature(visit.getTemperature());
        entity.setPulse(visit.getPulse());
        entity.setRespiratoryRate(visit.getRespiratoryRate());
        entity.setOxygenLevel(visit.getOxygenLevel());
        entity.setMedications(visit.getMedications());
        entity.setProcedures(visit.getProcedures());
        entity.setObservations(visit.getObservations());
        
        return entity;
    }
    
    public static Visit toDomain(VisitEntity entity) {
        if (entity == null) return null;
        
        Visit visit = new Visit();
        visit.setId(entity.getId().intValue());
        visit.setPatientId(entity.getPatient().getId());
        visit.setNurseId(entity.getNurse().getId());
        visit.setVisitDate(entity.getVisitDate());
        visit.setBloodPressure(entity.getBloodPressure());
        visit.setTemperature(entity.getTemperature());
        visit.setPulse(entity.getPulse());
        visit.setRespiratoryRate(entity.getRespiratoryRate());
        visit.setOxygenLevel(entity.getOxygenLevel());
        visit.setMedications(entity.getMedications());
        visit.setProcedures(entity.getProcedures());
        visit.setObservations(entity.getObservations());
        
        return visit;
    }
}