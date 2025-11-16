package app.adapter.in.rest.controllers;

import app.adapter.rest.mapper.AppointmentRestMapper;
import app.adapter.rest.mapper.EmergencyContactRestMapper;
import app.adapter.rest.mapper.InvoiceRestMapper;
import app.adapter.rest.mapper.MedicalInsuranceRestMapper;
import app.adapter.rest.mapper.MedicalOrderRestMapper;
import app.adapter.rest.mapper.PatientRestMapper;
import app.adapter.rest.request.AppointmentRequest;
import app.adapter.rest.request.EmergencyContactRequest;
import app.adapter.rest.request.InvoiceRequest;
import app.adapter.rest.request.MedicalInsuranceRequest;
import app.adapter.rest.request.PatientRequest;
import app.adapter.rest.response.AppointmentResponse;
import app.adapter.rest.response.EmergencyContactResponse;
import app.adapter.rest.response.InvoiceResponse;
import app.adapter.rest.response.MedicalInsuranceResponse;
import app.adapter.rest.response.PatientResponse;
import app.application.usecase.AppointmentUseCase;
import app.application.usecase.EmergencyContactUseCase;
import app.application.usecase.InvoiceUseCase;
import app.application.usecase.MedicalInsuranceUseCase;
import app.application.usecase.PatientUseCase;
import app.domain.model.EmergencyContact;
import app.domain.model.Invoice;
import app.domain.model.MedicalInsurance;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;
import app.domain.model.Appointment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMINISTRATIVE')")
public class AdministrativeController {

    @Autowired
    private PatientUseCase patientUseCase;

    @Autowired
    private EmergencyContactUseCase emergencyContactUseCase;

    @Autowired
    private MedicalInsuranceUseCase medicalInsuranceUseCase;

    @Autowired
    private AppointmentUseCase appointmentUseCase;

    @Autowired
    private InvoiceUseCase invoiceUseCase;

    // Mappers
    @Autowired
    private PatientRestMapper patientRestMapper;

    @Autowired
    private EmergencyContactRestMapper emergencyContactRestMapper;

    @Autowired
    private MedicalInsuranceRestMapper medicalInsuranceRestMapper;

    @Autowired
    private AppointmentRestMapper appointmentRestMapper;

    @Autowired
    private InvoiceRestMapper invoiceRestMapper;

    @Autowired
    private MedicalOrderRestMapper medicalOrderRestMapper;

    // CREAR PACIENTE
    @PostMapping("/patients")
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequest request) throws Exception {
        System.out.println("🎯 CREATE PATIENT endpoint hit");

        try {
            System.out
                    .println("📦 Request received - Patient: " + request.getFirstName() + " " + request.getLastName());

            Patient patient = patientRestMapper.toDomain(request);
            Patient savedPatient = patientUseCase.registerPatient(patient);

            System.out.println("✅ Patient created successfully - ID: " + savedPatient.getId());
            return new ResponseEntity<>(patientRestMapper.toResponse(savedPatient), HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println("❌ ERROR creating patient: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // ACTUALIZAR PACIENTE
    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id, @RequestBody PatientRequest request)
            throws Exception {
        System.out.println("🎯 UPDATE PATIENT endpoint hit - ID: " + id);

        try {
            Patient patient = patientRestMapper.toDomain(request);
            patient.setId(id);

            patientUseCase.updatePatient(patient);

            System.out.println("✅ Patient updated successfully - ID: " + id);
            return ResponseEntity.ok(patientRestMapper.toResponse(patient));

        } catch (Exception e) {
            System.out.println("❌ ERROR updating patient: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // BUSCAR PACIENTE POR DOCUMENTO
    @GetMapping("/patients/document/{documentId}")
    public ResponseEntity<PatientResponse> getPatientByDocument(@PathVariable Long documentId) throws Exception {
        System.out.println("🎯 GET PATIENT BY DOCUMENT endpoint hit - Document: " + documentId);

        Patient patient = patientUseCase.findPatientByIdNumber(documentId);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(patientRestMapper.toResponse(patient));
    }

    // ========== EMERGENCY CONTACT ENDPOINTS ==========

    @PostMapping("/patients/{patientId}/emergency-contact")
    public ResponseEntity<EmergencyContactResponse> createEmergencyContact(
            @PathVariable Long patientId,
            @RequestBody EmergencyContactRequest request) {

        System.out.println("🎯 CREATE EMERGENCY CONTACT endpoint hit for patient: " + patientId);

        try {
            EmergencyContact contact = emergencyContactRestMapper.toDomain(request);
            EmergencyContact savedContact = emergencyContactUseCase.createEmergencyContact(patientId, contact);
            EmergencyContactResponse response = emergencyContactRestMapper.toResponse(savedContact);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println("❌ ERROR creating emergency contact: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/patients/{patientId}/emergency-contact")
    public ResponseEntity<EmergencyContactResponse> getEmergencyContact(@PathVariable Long patientId) {
        System.out.println("🎯 GET EMERGENCY CONTACT endpoint hit for patient: " + patientId);

        EmergencyContact contact = emergencyContactUseCase.getEmergencyContactByPatientId(patientId);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }

        EmergencyContactResponse response = emergencyContactRestMapper.toResponse(contact);
        return ResponseEntity.ok(response);
    }

    // ========== MEDICAL INSURANCE ENDPOINTS ==========

    @PostMapping("/patients/{patientId}/insurance")
    public ResponseEntity<MedicalInsuranceResponse> createMedicalInsurance(
            @PathVariable Long patientId,
            @RequestBody MedicalInsuranceRequest request) {

        System.out.println("🎯 CREATE MEDICAL INSURANCE endpoint hit for patient: " + patientId);

        try {
            MedicalInsurance insurance = medicalInsuranceRestMapper.toDomain(request);
            MedicalInsurance savedInsurance = medicalInsuranceUseCase.createMedicalInsurance(patientId, insurance);
            MedicalInsuranceResponse response = medicalInsuranceRestMapper.toResponse(savedInsurance);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println("❌ ERROR creating medical insurance: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/patients/{patientId}/insurance")
    public ResponseEntity<MedicalInsuranceResponse> getMedicalInsurance(@PathVariable Long patientId) {
        System.out.println("🎯 GET MEDICAL INSURANCE endpoint hit for patient: " + patientId);

        MedicalInsurance insurance = medicalInsuranceUseCase.getMedicalInsuranceByPatientId(patientId);
        if (insurance == null) {
            return ResponseEntity.notFound().build();
        }

        MedicalInsuranceResponse response = medicalInsuranceRestMapper.toResponse(insurance);
        return ResponseEntity.ok(response);
    }

    // ========== APPOINTMENT ENDPOINTS ==========

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest request) {
        System.out.println("🎯 CREATE APPOINTMENT endpoint hit");

        try {
            Appointment appointment = appointmentRestMapper.toDomain(request);
            Appointment savedAppointment = appointmentUseCase.createAppointment(
                    appointment, request.getPatientId(), request.getMedicId()); // Cambiar a medicId

            AppointmentResponse response = appointmentRestMapper.toResponse(savedAppointment);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println("❌ ERROR creating appointment: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/patients/{patientId}/appointments")
    public ResponseEntity<List<AppointmentResponse>> getPatientAppointments(@PathVariable Long patientId) {
        System.out.println("🎯 GET PATIENT APPOINTMENTS endpoint hit for patient: " + patientId);

        List<Appointment> appointments = appointmentUseCase.getAppointmentsByPatientId(patientId);
        List<AppointmentResponse> responses = appointments.stream()
                .map(appointmentRestMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    // ========== INVOICE ENDPOINTS ==========

    @PostMapping("/invoices")
    public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody InvoiceRequest request) {
        System.out.println("🎯 CREATE INVOICE endpoint hit");

        // DEBUG DETALLADO
        System.out.println("🔍 DEBUG - InvoiceRequest received:");
        System.out.println("   invoiceDate: " + request.getInvoiceDate());
        System.out.println("   totalAmount: " + request.getTotalAmount());
        System.out.println("   patientId: " + request.getPatientId());
        System.out.println("   appointmentId: " + request.getAppointmentId());
        System.out.println("   medicalOrders: "
                + (request.getMedicalOrders() != null ? request.getMedicalOrders().size() : "null"));

        if (request.getPatientId() == null) {
            System.out.println("❌ ERROR: patientId is NULL in the request!");
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Convert medical orders
            List<MedicalOrder> medicalOrders = null;
            if (request.getMedicalOrders() != null) {
                medicalOrders = request.getMedicalOrders().stream()
                        .map(medicalOrderRestMapper::toDomain)
                        .collect(Collectors.toList());
            }

            // Create invoice
            Invoice invoice = invoiceRestMapper.toDomain(request);
            System.out.println("🔍 DEBUG - Invoice domain object created:");
            System.out.println(
                    "   Invoice patient: " + (invoice.getPatient() != null ? invoice.getPatient().getId() : "null"));

            Invoice savedInvoice = invoiceUseCase.createInvoice(
                    invoice, request.getPatientId(), request.getAppointmentId(), medicalOrders);

            InvoiceResponse response = invoiceRestMapper.toResponse(savedInvoice);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println("❌ ERROR creating invoice: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/patients/{patientId}/invoices")
    public ResponseEntity<List<InvoiceResponse>> getPatientInvoices(@PathVariable Long patientId) {
        System.out.println("🎯 GET PATIENT INVOICES endpoint hit for patient: " + patientId);

        List<Invoice> invoices = invoiceUseCase.getInvoicesByPatientId(patientId);
        List<InvoiceResponse> responses = invoices.stream()
                .map(invoiceRestMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/invoices/{invoiceId}")
    public ResponseEntity<InvoiceResponse> getInvoice(@PathVariable Long invoiceId) {
        System.out.println("🎯 GET INVOICE endpoint hit - ID: " + invoiceId);

        try {
            Invoice invoice = invoiceUseCase.getInvoiceById(invoiceId);
            InvoiceResponse response = invoiceRestMapper.toResponse(invoice);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR getting invoice: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}