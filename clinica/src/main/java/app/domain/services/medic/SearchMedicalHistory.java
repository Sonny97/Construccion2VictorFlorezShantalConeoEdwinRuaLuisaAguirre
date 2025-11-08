// package app.domain.services.medic;

// import app.domain.model.Patient;
// import app.domain.model.MedicalRegister;
// import app.domain.repository.PatientRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;


// @Service
// public class SearchMedicalHistory {

//     @Autowired
//     private PatientRepository patientRepository;

//     public MedicalRegister[] searchMedicaHistory(Patient patient) {
//         try {
//             patientRepository.findByIdNumber(patient.getId());
//         } catch (Exception e) {
//             System.out.println("Error: " + e.getMessage());
//         }
//         return patient.getMedicalRegister();
//     }

//     public Patient findByIdNumber(Long idNumber) {
//         return patientRepository.findByIdNumber(idNumber);
//     }
// }