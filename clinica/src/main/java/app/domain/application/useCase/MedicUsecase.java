
package app.domain.application.useCase;
import app.domain.model.emuns.*;



import java.time.LocalDate;

import app.domain.model.*;
import app.domain.repository.PatientRepository;
import app.domain.model.emuns.Role;

public class MedicUsecase {
    private final PatientRepository patientRepository;
    private final Role role;

    public managePatient(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void registerPatient(String firstName, String lastName, String documentId, 
                  LocalDate birthDate, String gender, String address, 
                  String phoneNumber, String emergencyContact, 
                  String allergies, String medicalConditions) {
        
        if (role != Role.MEDIC) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede registrar pacientes");
        }

        Patient patient = new Patient(firstName, lastName, documentId, birthDate, gender, address, phoneNumber, emergencyContact, allergies, medicalConditions);
        patientRepository.save(patient);
    }
}
