package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByPatientId(Long patientId);
    List<AppointmentEntity> findByMedicId(Long medicId); // Cambiar de doctorId a medicId
    List<AppointmentEntity> findByStatus(String status);
}