// package app.adapter.in.rest.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import app.application.useCase.MedicUsecase;
// import app.application.useCase.PatientUseCase;
// import app.domain.model.Patient;

// import java.util.List;

// @RestController
// @RequestMapping("/medic")
// public class MedicController {
//     private final MedicUsecase medicUsecase;
//     private final PatientUseCase patientUseCase;

//     @Autowired
//     public MedicController(Medic medic) { //aca en Medic llamar a modelo de medico 
//         this.medic = medic;
//     }

//     // Obtener un usuario por su ID
//     @GetMapping("/{id}")
//     public ResponseEntity<Patient> obtenerUsuarioPorId(Long id) {
//         Patient patient = patientUseCase.findPatientByIdNumber(id);
//         if (patient == null) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//         return new ResponseEntity<>(patient, HttpStatus.OK);
//     }

//     // Crear un nuevo usuario
//     @PostMapping
//     public ResponseEntity<Patient> managePatient(Patient patient) {
//         Patient patient = medicUsecase.managePatient(patient);
//         return new ResponseEntity<>(patient, HttpStatus.CREATED);
//     }

    
// }

// //podriamos mirarlo así


// // package app.adapter.in.rest.controllers;  

// // import app.adapter.rest.mapper.MedicalRecordRestMapper;
// // import app.adapter.rest.mapper.ClinicalOrderRestMapper;
// // import app.adapter.rest.request.MedicalRecordRequest;
// // import app.adapter.rest.request.ClinicalOrderRequest;
// // import app.adapter.rest.response.MedicalRecordResponse;
// // import app.adapter.rest.response.ClinicalOrderResponse;
// // import app.application.usecases.DoctorUseCase;
// // import app.domain.model.MedicalRecord;
// // import app.domain.model.ClinicalOrder;
// // import app.domain.model.Patient;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.security.access.prepost.PreAuthorize;
// // import org.springframework.web.bind.annotation.*;

// // import java.util.List;
// // import java.util.stream.Collectors;

// // @RestController
// // @RequestMapping("/api/doctor")
// // @PreAuthorize("hasRole('DOCTOR')")
// // public class DoctorController {

// //     @Autowired
// //     private DoctorUseCase doctorUseCase;

// //     @Autowired
// //     private MedicalRecordRestMapper medicalRecordRestMapper;

// //     @Autowired
// //     private ClinicalOrderRestMapper clinicalOrderRestMapper;

// //     @GetMapping("/medical-records/{patientId}")
// //     public ResponseEntity<List<MedicalRecordResponse>> searchMedicalHistory(@PathVariable String patientId) throws Exception {
// //         Patient patient = new Patient();
// //         patient.setId(Long.parseLong(patientId));
// //         List<MedicalRecord> records = doctorUseCase.searchMedicalHistory(patient);
// //         List<MedicalRecordResponse> res = records.stream()
// //                 .map(medicalRecordRestMapper::toResponse)
// //                 .collect(Collectors.toList());
// //         return ResponseEntity.ok(res);
// //     }

// //     @PostMapping("/medical-records")
// //     public ResponseEntity<MedicalRecordResponse> createMedicalRecord(@RequestBody MedicalRecordRequest request) throws Exception {
// //         MedicalRecord medicalRecord = medicalRecordRestMapper.toDomain(request);
// //         doctorUseCase.createMedicalRecord(medicalRecord);
// //         return new ResponseEntity<>(medicalRecordRestMapper.toResponse(medicalRecord), HttpStatus.CREATED);
// //     }

// //     @PutMapping("/medical-records/{id}")
// //     public ResponseEntity<MedicalRecordResponse> updateMedicalRecord(@PathVariable String id, @RequestBody MedicalRecordRequest request) throws Exception {
// //         MedicalRecord medicalRecord = medicalRecordRestMapper.toDomain(request);
// //         medicalRecord.setId(Long.parseLong(id));
// //         doctorUseCase.updateMedicalRecord(medicalRecord);
// //         return ResponseEntity.ok(medicalRecordRestMapper.toResponse(medicalRecord));
// //     }

// //     @PostMapping("/clinical-orders")
// //     public ResponseEntity<ClinicalOrderResponse> createClinicalOrder(@RequestBody ClinicalOrderRequest request) throws Exception {
// //         ClinicalOrder clinicalOrder = clinicalOrderRestMapper.toDomain(request);
// //         doctorUseCase.createClinicalOrder(clinicalOrder);
// //         return new ResponseEntity<>(clinicalOrderRestMapper.toResponse(clinicalOrder), HttpStatus.CREATED);
// //     }
// // }
