package app.domain.repository;

import app.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByFullNameContainingIgnoreCase(String fullName);
    List<Patient> findByFullName(String fullname);
    Patient findByIdNumber(Long idNumber);
    Patient Save(Patient patient);
}
