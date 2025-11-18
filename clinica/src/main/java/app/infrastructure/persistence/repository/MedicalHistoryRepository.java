package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para Historias Clínicas
 */
@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistoryEntity, Long> {
    
    /**
     * Busca una historia clínica por el ID del paciente
     */
    Optional<MedicalHistoryEntity> findByPatientId(Long patientId);
    
    /**
     * Verifica si existe una historia clínica para un paciente
     */
    boolean existsByPatientId(Long patientId);
}
