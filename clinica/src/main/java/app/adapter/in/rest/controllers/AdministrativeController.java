// package app.adapter.in.rest.controllers;

// import app.adapter.rest.mapper.EmployeeRestMapper;
// import app.adapter.rest.request.EmployeeRequest;
// import app.adapter.rest.response.EmployeeResponse;
// import app.application.useCase.HumanResourcesUseCase;
// import app.domain.model.Employee;
// //import app.adapter.rest.mapper.PatientRestMapper;
// //import app.adapter.rest.mapper.AppointmentRestMapper;
// //import app.adapter.rest.mapper.InvoiceRestMapper;
// //import app.adapter.rest.mapper.EmergencyContactRestMapper;
// //import app.adapter.rest.request.PatientRequest;
// //import app.adapter.rest.request.AppointmentRequest;
// //import app.adapter.rest.request.InvoiceRequest;
// //import app.adapter.rest.request.EmergencyContactRequest;
// //import app.adapter.rest.response.PatientResponse;
// //import app.adapter.rest.response.AppointmentResponse;
// //import app.adapter.rest.response.InvoiceResponse;
// //import app.adapter.rest.response.EmergencyContactResponse;
// //import app.application.usecases.AdministrativeUseCase;
// import app.domain.model.Patient;
// import app.domain.services.Appointment;

// //import app.domain.model.Appointment;
// //import app.domain.model.Invoice;
// //import app.domain.model.EmergencyContact;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/administrative")
// @PreAuthorize("hasRole('ADMINISTRATIVE')")
// public class AdministrativeController {

//     @Autowired
//     private HumanResourcesUseCase administrativeUseCase;

//       @Autowired
//     private EmployeeRestMapper employeeRestMapper;

//     @Autowired
//     //private PatientRestMapper patientRestMapper;

//     //@Autowired
//     //private AppointmentRestMapper appointmentRestMapper;
// //
//     //@Autowired
//     //private InvoiceRestMapper invoiceRestMapper;
// //
//     //@Autowired
//     //private EmergencyContactRestMapper emergencyContactRestMapper;

   

//     @PostMapping("/patients")
//     public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequest request) throws Exception {
//         Patient patient = patientRestMapper.toDomain(request);
//         administrativeUseCase.createPatient(patient);
//         return new ResponseEntity<>(patientRestMapper.toResponse(patient), HttpStatus.CREATED);
//     }

//     @PutMapping("/patients/{id}")
//     public ResponseEntity<PatientResponse> updatePatient(@PathVariable String id, @RequestBody PatientRequest request) throws Exception {
//         Patient patient = patientRestMapper.toDomain(request);
//         patient.setId(Long.parseLong(id));
//         administrativeUseCase.updatePatient(patient);
//         return ResponseEntity.ok(patientRestMapper.toResponse(patient));
//     }

//     @PostMapping("/appointments")
//     public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest request) throws Exception {
//         Appointment appointment = appointmentRestMapper.toDomain(request);
//         administrativeUseCase.createAppointment(appointment);
//         return new ResponseEntity<>(appointmentRestMapper.toResponse(appointment), HttpStatus.CREATED);
//     }

//     @PostMapping("/invoices")
//     public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody InvoiceRequest request) throws Exception {
//         Invoice invoice = invoiceRestMapper.toDomain(request);
//         administrativeUseCase.createInvoice(invoice);
//         return new ResponseEntity<>(invoiceRestMapper.toResponse(invoice), HttpStatus.CREATED);
//     }

//     @PostMapping("/emergency-contacts")
//     public ResponseEntity<EmergencyContactResponse> createEmergencyContact(@RequestBody EmergencyContactRequest request) throws Exception {
//         EmergencyContact emergencyContact = emergencyContactRestMapper.toDomain(request);
//         administrativeUseCase.createEmergencyContact(emergencyContact);
//         return new ResponseEntity<>(emergencyContactRestMapper.toResponse(emergencyContact), HttpStatus.CREATED);
//     }
// }