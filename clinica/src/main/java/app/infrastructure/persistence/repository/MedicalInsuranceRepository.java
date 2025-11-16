package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.MedicalInsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MedicalInsuranceRepository extends JpaRepository<MedicalInsuranceEntity, Long> {
    Optional<MedicalInsuranceEntity> findByPatientId(Long patientId);
    boolean existsByPatientId(Long patientId);
    Optional<MedicalInsuranceEntity> findByPolicyNumber(String policyNumber);
}