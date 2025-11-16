package app.adapter.in.rest.controllers;

import app.adapter.rest.mapper.MedicalRegisterRestMapper;
import app.adapter.rest.request.MedicalRegisterRequest;
import app.adapter.rest.response.MedicalRegisterResponse;
import app.application.usecase.MedicalRegisterUseCase;
import app.domain.model.MedicalRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medical-registers")
public class MedicalRegisterController {

    @Autowired
    private MedicalRegisterUseCase medicalRegisterUseCase;

    @Autowired
    private MedicalRegisterRestMapper medicalRegisterRestMapper;

    /**
     * Crear un nuevo registro médico
     * Roles permitidos: MEDIC, NURSE
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE')")
    public ResponseEntity<MedicalRegisterResponse> createMedicalRegister(@RequestBody MedicalRegisterRequest request) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - CREATE MEDICAL REGISTER");
            System.out.println("📦 Request received - Patient ID: " + request.getPatientId() 
                             + ", Medic ID: " + request.getMedicId());

            MedicalRegister medicalRegister = medicalRegisterRestMapper.toDomain(request);
            MedicalRegister createdRegister = medicalRegisterUseCase.createMedicalRegister(medicalRegister);

            System.out.println("✅ SUCCESS - Medical register created with ID: " + createdRegister.getId());
            return new ResponseEntity<>(medicalRegisterRestMapper.toResponse(createdRegister), HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtener todos los registros médicos
     * Roles permitidos: MEDIC, NURSE, ADMINISTRATIVE, HUMAN_RESOURCES
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE', 'ADMINISTRATIVE', 'HUMAN_RESOURCES')")
    public ResponseEntity<List<MedicalRegisterResponse>> getAllMedicalRegisters() {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET ALL MEDICAL REGISTERS");

            List<MedicalRegister> registers = medicalRegisterUseCase.listAllMedicalRegisters();
            List<MedicalRegisterResponse> response = registers.stream()
                    .map(medicalRegisterRestMapper::toResponse)
                    .collect(Collectors.toList());

            System.out.println("✅ SUCCESS - Retrieved " + registers.size() + " medical registers");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener un registro médico por ID
     * Roles permitidos: MEDIC, NURSE, ADMINISTRATIVE
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE', 'ADMINISTRATIVE')")
    public ResponseEntity<MedicalRegisterResponse> getMedicalRegisterById(@PathVariable Long id) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET MEDICAL REGISTER BY ID: " + id);

            MedicalRegister medicalRegister = medicalRegisterUseCase.getMedicalRegisterById(id);
            MedicalRegisterResponse response = medicalRegisterRestMapper.toResponse(medicalRegister);

            System.out.println("✅ SUCCESS - Medical register found: " + id);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtener registros médicos por paciente
     * Roles permitidos: MEDIC, NURSE
     */
    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE')")
    public ResponseEntity<List<MedicalRegisterResponse>> getMedicalRegistersByPatient(@PathVariable Long patientId) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET MEDICAL REGISTERS BY PATIENT ID: " + patientId);

            List<MedicalRegister> registers = medicalRegisterUseCase.listMedicalRegistersByPatient(patientId);
            List<MedicalRegisterResponse> response = registers.stream()
                    .map(medicalRegisterRestMapper::toResponse)
                    .collect(Collectors.toList());

            System.out.println("✅ SUCCESS - Retrieved " + registers.size() + " medical registers for patient " + patientId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtener registros médicos por médico
     * Roles permitidos: MEDIC, HUMAN_RESOURCES
     */
    @GetMapping("/medic/{medicId}")
    @PreAuthorize("hasAnyRole('MEDIC', 'HUMAN_RESOURCES')")
    public ResponseEntity<List<MedicalRegisterResponse>> getMedicalRegistersByMedic(@PathVariable Long medicId) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET MEDICAL REGISTERS BY MEDIC ID: " + medicId);

            List<MedicalRegister> registers = medicalRegisterUseCase.listMedicalRegistersByMedic(medicId);
            List<MedicalRegisterResponse> response = registers.stream()
                    .map(medicalRegisterRestMapper::toResponse)
                    .collect(Collectors.toList());

            System.out.println("✅ SUCCESS - Retrieved " + registers.size() + " medical registers for medic " + medicId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualizar un registro médico existente
     * Roles permitidos: MEDIC, NURSE
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE')")
    public ResponseEntity<MedicalRegisterResponse> updateMedicalRegister(
            @PathVariable Long id, 
            @RequestBody MedicalRegisterRequest request) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - UPDATE MEDICAL REGISTER ID: " + id);

            MedicalRegister medicalRegister = medicalRegisterRestMapper.toDomain(request);
            medicalRegister.setId(id);
            MedicalRegister updatedRegister = medicalRegisterUseCase.updateMedicalRegister(medicalRegister);

            System.out.println("✅ SUCCESS - Medical register updated: " + id);
            return ResponseEntity.ok(medicalRegisterRestMapper.toResponse(updatedRegister));

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
