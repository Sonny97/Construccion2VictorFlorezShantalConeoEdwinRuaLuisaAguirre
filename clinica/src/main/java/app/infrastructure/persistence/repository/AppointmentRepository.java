package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByPatientId(Long patientId);
    List<AppointmentEntity> findByDoctorId(Long doctorId);
    List<AppointmentEntity> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
    List<AppointmentEntity> findByStatus(String status);
    List<AppointmentEntity> findByPatientIdAndStatus(Long patientId, String status);
}