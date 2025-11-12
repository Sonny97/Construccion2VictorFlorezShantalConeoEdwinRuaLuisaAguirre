package app.adapter.in.rest.controllers;

import app.adapter.rest.mapper.VisitRestMapper;
import app.adapter.in.rest.request.VisitRequest;
import app.adapter.rest.response.VisitResponse;
import app.application.usecase.*;
import app.domain.model.Visit;
import app.domain.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurse")
@PreAuthorize("hasRole('NURSE')")
public class NurseController {

    @Autowired
    private VisitUseCase nurseUseCase;

    @Autowired
    private VisitRestMapper visitRestMapper;

    // 1. REGISTRAR VISITA
    @PostMapping("/visits")
    public ResponseEntity<VisitResponse> registerVisit(@RequestBody VisitRequest request) throws Exception {
        System.out.println("🎯 REGISTER VISIT endpoint hit");
        
        try {
            System.out.println("📦 Visit for patient: " + request.getPatientId() + " by nurse: " + request.getNurseId());
            
            Visit visit = visitRestMapper.toDomain(request);
            Visit savedVisit = nurseUseCase.registerVisit(visit);
            
            System.out.println("✅ Visit registered successfully - ID: " + savedVisit.getId());
            return new ResponseEntity<>(visitRestMapper.toResponse(savedVisit), HttpStatus.CREATED);
            
        } catch (Exception e) {
            System.out.println("❌ ERROR registering visit: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 2. BUSCAR PACIENTE POR ID
    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> searchPatient(@PathVariable Long id) throws Exception {
        System.out.println("🎯 SEARCH PATIENT BY ID endpoint hit - ID: " + id);
        
        Patient patient = nurseUseCase.searchPatient(id);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        
        System.out.println("✅ Patient found: " + patient.getFirstName() + " " + patient.getLastName());
        return ResponseEntity.ok(patient);
    }

    // 3. BUSCAR VISITAS DE UN PACIENTE
    @GetMapping("/patients/{patientId}/visits")
    public ResponseEntity<List<VisitResponse>> getPatientVisits(@PathVariable Long patientId) throws Exception {
        System.out.println("🎯 GET PATIENT VISITS endpoint hit - Patient ID: " + patientId);
        
        List<Visit> visits = nurseUseCase.getPatientVisits(patientId);
        List<VisitResponse> response = visits.stream()
                .map(visitRestMapper::toResponse)
                .toList();
        
        System.out.println("✅ Found " + visits.size() + " visits for patient: " + patientId);
        return ResponseEntity.ok(response);
    }
}