package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.EmergencyContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContactEntity, Long> {
    Optional<EmergencyContactEntity> findByPatientId(Long patientId);
    boolean existsByPatientId(Long patientId);
}