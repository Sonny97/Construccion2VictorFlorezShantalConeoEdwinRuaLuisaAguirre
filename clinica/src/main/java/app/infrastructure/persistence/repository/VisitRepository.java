package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
    
    // Buscar visitas por ID del paciente
    List<VisitEntity> findByPatientId(Long patientId);
    
    // Buscar visitas por ID de la enfermera
    List<VisitEntity> findByNurseId(Long nurseId);
    
    // Buscar visitas por paciente y enfermera
    List<VisitEntity> findByPatientIdAndNurseId(Long patientId, Long nurseId);
}