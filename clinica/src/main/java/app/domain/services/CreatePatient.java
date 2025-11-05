// package app.domain.services;

// import app.domain.model.Patient;
// import app.infrastructure.persistence.repository.PatientRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class CreatePatient {

//     @Autowired
//     private PatientRepository patientRepository;

//     public Patient registerPatient(Patient patient) {
//         try {
//             patientRepository.findByIdNumber(patient.getId());
//         } catch (Exception e) {
//             System.out.println("Error: " + e.getMessage());
//         }
//         patientRepository.MedicalRegister(patient.getBirthDate(), patient.getId(), patient.getMedicalRegister(), patient.getMedicalConditions(), "diagnostico")
//         return patientRepository.save(patient);
//         System.out.println("Paciente registrado exitosamente");
//     }

//     public Patient findByIdNumber(Long idNumber) {
//         return patientRepository.findByIdNumber(idNumber);
//     }

//     public List<Patient> findByFullName(String fullName) {
//         return patientRepository.findByFullNameContainingIgnoreCase(fullName);
//     }
// }
