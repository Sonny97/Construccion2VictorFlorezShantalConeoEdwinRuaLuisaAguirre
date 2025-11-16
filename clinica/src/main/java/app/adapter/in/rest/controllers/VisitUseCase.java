package app.adapter.in.rest.controllers;

import app.domain.model.Visit;
import app.domain.model.Patient;
import app.domain.services.VisitService;
import app.domain.services.SearchPatientService;
// import app.adapter.in.builder.VisitBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitUseCase {
    
    @Autowired
    private VisitService visitService;

    @Autowired
    private SearchPatientService searchPatientService;

    // REGISTRAR VISITA - SIMPLIFICADO
    public Visit registerVisit(Visit visit) {
        System.out.println("👩‍⚕️ NurseUseCase - Registering visit for patient: " + visit.getPatientId());
        return visitService.registerVisit(visit);
    }

    // BUSCAR PACIENTE POR ID
    public Patient searchPatient(Long patientId) {
        System.out.println("🔍 NurseUseCase - Searching patient by ID: " + patientId);
        return searchPatientService.findPatientById(patientId);
    }

    // BUSCAR VISITAS DE UN PACIENTE
    public List<Visit> getPatientVisits(Long patientId) {
        System.out.println("📋 NurseUseCase - Getting visits for patient: " + patientId);
        return visitService.getVisitsByPatient(patientId);
    }
}