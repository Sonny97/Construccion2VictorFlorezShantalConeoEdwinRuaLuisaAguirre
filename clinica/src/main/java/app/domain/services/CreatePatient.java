package app.domain.services;

import app.domain.model.Patient;
import app.domain.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatePatient {

    @Autowired
    private PatientRepository patientRepository;

    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findByIdNumber(String idNumber) {
        return patientRepository.findByIdNumber(idNumber);
    }

    public List<Patient> findByFullName(String fullName) {
        return patientRepository.findByFullNameContainingIgnoreCase(fullName);
    }
}
