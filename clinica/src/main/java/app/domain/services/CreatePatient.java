package app.domain.services;

import app.domain.model.Patient;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreatePatient {

    @Autowired
    private PatientRepository patientRepository;

    public Patient registerPatient(Patient patient) {
        System.out.println("📝 Registering patient: " + patient.getFirstName() + " " + patient.getLastName());
        
        // Validar que no exista paciente con mismo documentId
        PatientEntity existingPatient = patientRepository.findByDocumentId(patient.getDocumentId());
        if (existingPatient != null) {
            throw new RuntimeException("Ya existe un paciente con el documento: " + patient.getDocumentId());
        }
        
        // Convertir a Entity y guardar
        PatientEntity patientEntity = PatientMapper.toEntity(patient);
        PatientEntity savedEntity = patientRepository.save(patientEntity);
        
        System.out.println("✅ Paciente registrado exitosamente - ID: " + savedEntity.getId());
        
        // Convertir de vuelta a Domain
        return PatientMapper.toDomain(savedEntity);
    }

    public Patient findByIdNumber(Long idNumber) {
        PatientEntity entity = patientRepository.findByDocumentId(idNumber);
        return entity != null ? PatientMapper.toDomain(entity) : null;
    }

    public List<Patient> findByFullName(String fullName) {
        List<PatientEntity> entities = patientRepository.findByFullNameContainingIgnoreCase(fullName);
        return entities.stream()
                .map(PatientMapper::toDomain)
                .collect(Collectors.toList());
    }
}