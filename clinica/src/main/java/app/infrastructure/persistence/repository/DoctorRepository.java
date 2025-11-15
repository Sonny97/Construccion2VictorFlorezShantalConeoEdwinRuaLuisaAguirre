package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Optional<DoctorEntity> findByLicenseNumber(String licenseNumber);
    Optional<DoctorEntity> findByUserId(Long userId);
    List<DoctorEntity> findBySpecialization(String specialization);
}