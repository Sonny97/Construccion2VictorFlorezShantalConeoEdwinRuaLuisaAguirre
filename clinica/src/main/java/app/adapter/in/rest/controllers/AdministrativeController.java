package app.adapter.in.rest.controllers;

import app.adapter.rest.mapper.PatientRestMapper;
import app.adapter.rest.request.PatientRequest;
import app.adapter.rest.response.PatientResponse;
import app.application.usecase.PatientUseCase;
import app.domain.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administrative")
@PreAuthorize("hasRole('ADMINISTRATIVE')")
public class AdministrativeController {

    @Autowired
    private PatientUseCase patientUseCase;

    @Autowired
    private PatientRestMapper patientRestMapper;

    // CREAR PACIENTE
    @PostMapping("/patients")
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequest request) throws Exception {
        System.out.println("🎯 CREATE PATIENT endpoint hit");
        
        try {
            System.out.println("📦 Request received - Patient: " + request.getFirstName() + " " + request.getLastName());
            
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
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id, @RequestBody PatientRequest request) throws Exception {
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

    // BUSCAR PACIENTES POR NOMBRE
    @GetMapping("/patients/search")
    public ResponseEntity<?> searchPatientsByName(@RequestParam String name) throws Exception {
        System.out.println("🎯 SEARCH PATIENTS BY NAME endpoint hit - Name: " + name);
        
        // Este método retornará List<Patient> - necesitamos crear el mapper para lista
        return ResponseEntity.ok("Search functionality - to be implemented");
    }
}