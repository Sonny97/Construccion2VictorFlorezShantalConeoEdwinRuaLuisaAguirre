package app.domain.services;

import app.domain.model.Patient;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchPatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient findPatientById(Long patientId) {
        System.out.println("🔍 SearchPatientService - Searching patient by ID: " + patientId);
        PatientEntity entity = patientRepository.findById(patientId).orElse(null);
        
        if (entity != null) {
            System.out.println("✅ Patient found: " + entity.getFirstName() + " " + entity.getLastName());
        } else {
            System.out.println("❌ Patient not found with ID: " + patientId);
        }
        
        return entity != null ? PatientMapper.toDomain(entity) : null;
    }

    public Patient findPatientByDocument(Long documentId) {
        System.out.println("🔍 SearchPatientService - Searching patient by document: " + documentId);
        PatientEntity entity = patientRepository.findByDocumentId(documentId);
        return entity != null ? PatientMapper.toDomain(entity) : null;
    }
}