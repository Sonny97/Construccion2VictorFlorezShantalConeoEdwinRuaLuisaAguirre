
package domain.usecases;

import domain.model.*;
import domain.ports.PatientRepository;

public class MedicUseCase {
    private final PacienteRepository pacienteRepository;

    public managePatient(PatientRepository patientRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void registerPatient(String firstName, String lastName, String documentId, 
                  LocalDate birthDate, String gender, String address, 
                  String phoneNumber, String emergencyContact, 
                  String allergies, String medicalConditions) {
        
        if (rol != Rol.MEDIC) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede registrar pacientes");
        }

        Patient patient = new Patient(firstName, lastName, documentId, birthDate, gender, address, phoneNumber, emergencyContact, allergies, medicalConditions);
        patientRepository.save(patient);
    }
}
