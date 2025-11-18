package app.adapter.out.persistence;

import app.domain.model.MedicalHistory;
import app.domain.ports.MedicalHistoryPort;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import app.infrastructure.persistence.mapper.MedicalHistoryMapper;
import app.infrastructure.persistence.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Adaptador de persistencia para Historias Clínicas.
 * Implementa el puerto de salida MedicalHistoryPort usando Spring Data JPA.
 */
@Service
public class MedicalHistoryAdapter implements MedicalHistoryPort {

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public MedicalHistory save(MedicalHistory medicalHistory) {
        MedicalHistoryEntity entity = MedicalHistoryMapper.toEntity(medicalHistory);
        MedicalHistoryEntity savedEntity = medicalHistoryRepository.save(entity);
        return MedicalHistoryMapper.toDomain(savedEntity);
    }

    @Override
    public MedicalHistory findById(Long id) {
        return medicalHistoryRepository.findById(id)
                .map(MedicalHistoryMapper::toDomain)
                .orElse(null);
    }

    @Override
    public MedicalHistory findByPatientId(Long patientId) {
        return medicalHistoryRepository.findByPatientId(patientId)
                .map(MedicalHistoryMapper::toDomain)
                .orElse(null);
    }

    @Override
    public boolean existsByPatientId(Long patientId) {
        return medicalHistoryRepository.existsByPatientId(patientId);
    }
}
