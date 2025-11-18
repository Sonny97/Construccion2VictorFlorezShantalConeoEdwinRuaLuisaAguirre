package app.adapter.in.rest.controllers;

import app.adapter.rest.mapper.MedicalHistoryRestMapper;
import app.adapter.rest.request.MedicalHistoryRequest;
import app.adapter.rest.response.MedicalHistoryResponse;
import app.application.usecase.MedicalHistoryUseCase;
import app.domain.model.MedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar las Historias Clínicas.
 * Solo los médicos tienen acceso a estos endpoints.
 */
@RestController
@RequestMapping("/api/medical-histories")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryUseCase medicalHistoryUseCase;

    @Autowired
    private MedicalHistoryRestMapper medicalHistoryRestMapper;

    /**
     * Crear una nueva historia clínica
     * Solo médicos pueden crear historias clínicas
     */
    @PostMapping
    @PreAuthorize("hasRole('MEDIC')")
    public ResponseEntity<MedicalHistoryResponse> createMedicalHistory(@RequestBody MedicalHistoryRequest request) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - CREATE MEDICAL HISTORY");
            System.out.println("📦 Request received - Patient ID: " + request.getPatientId() 
                             + ", Patient Name: " + request.getPatientName());

            MedicalHistory medicalHistory = medicalHistoryRestMapper.toDomain(request);
            MedicalHistory createdHistory = medicalHistoryUseCase.createMedicalHistory(medicalHistory);

            System.out.println("✅ SUCCESS - Medical history created with ID: " + createdHistory.getId());
            return new ResponseEntity<>(medicalHistoryRestMapper.toResponse(createdHistory), HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println("❌ ERROR creating medical history: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Consultar la historia clínica completa de un paciente
     * Solo médicos pueden consultar historias clínicas
     */
    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('MEDIC')")
    public ResponseEntity<MedicalHistoryResponse> getMedicalHistoryByPatient(@PathVariable Long patientId) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET MEDICAL HISTORY BY PATIENT ID: " + patientId);

            MedicalHistory medicalHistory = medicalHistoryUseCase.getMedicalHistoryByPatientId(patientId);
            MedicalHistoryResponse response = medicalHistoryRestMapper.toResponse(medicalHistory);

            System.out.println("✅ SUCCESS - Medical history found for patient: " + patientId 
                             + " with " + (medicalHistory.getMedicalRegisters() != null 
                                        ? medicalHistory.getMedicalRegisters().size() : 0) + " registers");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR retrieving medical history: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Consultar una historia clínica por su ID
     * Solo médicos pueden consultar historias clínicas
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MEDIC')")
    public ResponseEntity<MedicalHistoryResponse> getMedicalHistoryById(@PathVariable Long id) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET MEDICAL HISTORY BY ID: " + id);

            MedicalHistory medicalHistory = medicalHistoryUseCase.getMedicalHistoryById(id);
            MedicalHistoryResponse response = medicalHistoryRestMapper.toResponse(medicalHistory);

            System.out.println("✅ SUCCESS - Medical history found with ID: " + id);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR retrieving medical history by ID: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
