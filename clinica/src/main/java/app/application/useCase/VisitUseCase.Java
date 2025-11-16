package app.application.usecase;

import app.domain.model.Visit;
import app.domain.services.VisitService;
import app.adapter.in.builder.VisitBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitUseCase {
    
    private VisitService visitService;
    private VisitBuilder visitBuilder;

    @Autowired
    public VisitUseCase(VisitService visitService, VisitBuilder visitBuilder) {
        this.visitService = visitService;
        this.visitBuilder = visitBuilder;
    }

    public Visit registerPatientVisit(long patientId, long nurseId, String bloodPressure,
                                     double temperature, int pulse, double oxygenLevel,
                                     String medications, String procedures, String observations) {
        Visit visit = visitBuilder.build(patientId, nurseId, bloodPressure, temperature, pulse,
                                         oxygenLevel, medications, procedures, observations);
        return visitService.registerVisit(visit);
    }

    // 🔥 AGREGAR MÉTODOS FALTANTES para el controller
    public Visit registerVisit(Visit visit) {
        System.out.println("👩‍⚕️ Registering visit via NurseUseCase");
        return visitService.registerVisit(visit);
    }

    public app.domain.model.Patient searchPatient(Long patientId) {
        // Por implementar - necesitamos SearchPatientService
        System.out.println("🔍 Searching patient by ID: " + patientId);
        return null;
    }

    public app.domain.model.Patient searchPatientByDocument(Long documentId) {
        // Por implementar - necesitamos SearchPatientService  
        System.out.println("🔍 Searching patient by document: " + documentId);
        return null;
    }
    public List<ClinicalOrder> searchClinicalOrders(Patient patient) {
    System.out.println("📋 Searching clinical orders for patient: " + patient.getId());
    // Por implementar - retornar lista vacía por ahora
    return List.of();
}
}