package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.application.useCase.MedicUsecase;
import app.application.useCase.PatientUseCase;
import app.domain.model.Patient;

import java.util.List;

@RestController
@RequestMapping("/medic")
public class Medic {
    private final MedicUsecase medicUsecase;
    private final PatientUseCase patientUseCase;

    @Autowired
    public MedicController(Medic medic) {
        this.medic = medic;
    }

    // Obtener un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> obtenerUsuarioPorId(Long id) {
        Patient patient = patientUseCase.findPatientByIdNumber(id);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Patient> managePatient(Patient patient) {
        Patient patient = medicUsecase.managePatient(patient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }
}
