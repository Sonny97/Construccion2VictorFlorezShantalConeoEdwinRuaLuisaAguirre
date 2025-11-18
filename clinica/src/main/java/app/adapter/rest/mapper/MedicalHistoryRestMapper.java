package app.adapter.rest.mapper;

import app.adapter.rest.request.MedicalHistoryRequest;
import app.adapter.rest.response.MedicalHistoryResponse;
import app.adapter.rest.response.MedicalRegisterResponse;
import app.domain.model.MedicalHistory;
import app.domain.model.MedicalRegister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre DTOs y entidades de dominio de MedicalHistory
 */
@Component
public class MedicalHistoryRestMapper {

    private final MedicalRegisterRestMapper medicalRegisterRestMapper;

    public MedicalHistoryRestMapper(MedicalRegisterRestMapper medicalRegisterRestMapper) {
        this.medicalRegisterRestMapper = medicalRegisterRestMapper;
    }

    /**
     * Convierte un Request DTO a una entidad de dominio
     */
    public MedicalHistory toDomain(MedicalHistoryRequest request) {
        if (request == null) {
            return null;
        }

        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setPatientId(request.getPatientId());
        medicalHistory.setPatientName(request.getPatientName());
        medicalHistory.setBloodType(request.getBloodType());
        medicalHistory.setAllergies(request.getAllergies());
        medicalHistory.setChronicDiseases(request.getChronicDiseases());
        medicalHistory.setFamilyHistory(request.getFamilyHistory());
        medicalHistory.setSurgicalHistory(request.getSurgicalHistory());
        medicalHistory.setObservations(request.getObservations());

        return medicalHistory;
    }

    /**
     * Convierte una entidad de dominio a un Response DTO
     */
    public MedicalHistoryResponse toResponse(MedicalHistory medicalHistory) {
        if (medicalHistory == null) {
            return null;
        }

        // Convertir los registros médicos a Response DTOs
        List<MedicalRegisterResponse> registerResponses = null;
        if (medicalHistory.getMedicalRegisters() != null) {
            registerResponses = medicalHistory.getMedicalRegisters().stream()
                    .map(medicalRegisterRestMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return new MedicalHistoryResponse(
                medicalHistory.getId(),
                medicalHistory.getPatientId(),
                medicalHistory.getPatientName(),
                medicalHistory.getCreationDate(),
                medicalHistory.getLastUpdateDate(),
                medicalHistory.getBloodType(),
                medicalHistory.getAllergies(),
                medicalHistory.getChronicDiseases(),
                medicalHistory.getFamilyHistory(),
                medicalHistory.getSurgicalHistory(),
                medicalHistory.getObservations(),
                registerResponses
        );
    }
}
