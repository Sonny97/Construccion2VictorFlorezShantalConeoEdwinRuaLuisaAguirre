package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.MedicalRegisterEntity;
import java.util.List;

@Repository
public interface MedicaRepository extends JpaRepository<MedicalRegisterEntity, Long> {
    
    List<MedicalRegisterEntity> findByPatientId(Long patientId);
    
    List<MedicalRegisterEntity> findByMedicId(Long medicId);
    
    List<MedicalRegisterEntity> findByPatientIdOrderByRegisterDateDesc(Long patientId);
    
    List<MedicalRegisterEntity> findByMedicIdOrderByRegisterDateDesc(Long medicId);
}
