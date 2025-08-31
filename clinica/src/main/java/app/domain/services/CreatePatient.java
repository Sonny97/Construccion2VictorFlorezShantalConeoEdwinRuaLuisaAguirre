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
        try {
            patientRepository.findByIdNumber(patient.idNumber);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return patientRepository.save(patient);
        System.out.println("Paciente registrado exitosamente");
    }

    public Patient findByIdNumber(Long idNumber) {
        return patientRepository.findByIdNumber(idNumber);
    }

    public List<Patient> findByFullName(String fullName) {
        return patientRepository.findByFullNameContainingIgnoreCase(fullName);
    }
}
