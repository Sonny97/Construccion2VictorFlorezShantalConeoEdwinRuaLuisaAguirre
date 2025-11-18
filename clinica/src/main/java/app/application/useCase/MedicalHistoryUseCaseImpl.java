package app.application.usecase;

import app.domain.model.MedicalHistory;
import app.domain.services.CreateMedicalHistoryService;
import app.domain.services.GetMedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del caso de uso para gestionar Historias Clínicas.
 * Coordina los servicios de dominio para crear y consultar historias clínicas.
 */
@Service
public class MedicalHistoryUseCaseImpl implements MedicalHistoryUseCase {

    @Autowired
    private CreateMedicalHistoryService createMedicalHistoryService;

    @Autowired
    private GetMedicalHistoryService getMedicalHistoryService;

    @Override
    public MedicalHistory createMedicalHistory(MedicalHistory medicalHistory) {
        try {
            return createMedicalHistoryService.create(medicalHistory);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la historia clínica: " + e.getMessage(), e);
        }
    }

    @Override
    public MedicalHistory getMedicalHistoryByPatientId(Long patientId) {
        try {
            return getMedicalHistoryService.getByPatientId(patientId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la historia clínica del paciente: " + e.getMessage(), e);
        }
    }

    @Override
    public MedicalHistory getMedicalHistoryById(Long id) {
        try {
            return getMedicalHistoryService.getById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la historia clínica: " + e.getMessage(), e);
        }
    }
}
