package app.adapter.in.rest.controllers;

import app.adapter.rest.mapper.VisitRestMapper;
import app.adapter.rest.mapper.ClinicalOrderRestMapper;
import app.adapter.rest.request.VisitRequest;
import app.adapter.rest.response.VisitResponse;
import app.adapter.rest.response.ClinicalOrderResponse;
import app.application.usecases.NurseUseCase;
import app.domain.model.Visit;
import app.domain.model.ClinicalOrder;
import app.domain.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nurse")
@PreAuthorize("hasRole('NURSE')")
public class NurseController {

    @Autowired
    private NurseUseCase nurseUseCase;

    @Autowired
    private VisitRestMapper visitRestMapper;

    @Autowired
    private ClinicalOrderRestMapper clinicalOrderRestMapper;

    @PostMapping("/visits")
    public ResponseEntity<VisitResponse> registerVisit(@RequestBody VisitRequest request) throws Exception {
        Visit visit = visitRestMapper.toDomain(request);
        nurseUseCase.registerVisit(visit);
        return new ResponseEntity<>(visitRestMapper.toResponse(visit), HttpStatus.CREATED);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> searchPatient(@PathVariable String id) throws Exception {
        Patient patient = nurseUseCase.searchPatient(Long.parseLong(id));
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/clinical-orders/{patientId}")
    public ResponseEntity<List<ClinicalOrderResponse>> searchClinicalOrders(@PathVariable String patientId) throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.parseLong(patientId));
        List<ClinicalOrder> orders = nurseUseCase.searchClinicalOrders(patient);
        List<ClinicalOrderResponse> res = orders.stream()
                .map(clinicalOrderRestMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }
}