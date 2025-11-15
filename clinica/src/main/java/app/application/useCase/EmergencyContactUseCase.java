package app.application.usecase;

import app.domain.model.EmergencyContact;
import app.domain.model.Patient;
import app.domain.services.EmergencyContactService;
import app.infrastructure.persistence.entities.EmergencyContactEntity;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.repository.EmergencyContactRepository;
import app.infrastructure.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class EmergencyContactUseCase {

    @Autowired
    private EmergencyContactService emergencyContactService;

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Autowired
    private PatientRepository patientRepository;

    public EmergencyContact createEmergencyContact(Long patientId, EmergencyContact contact) {
        // Find patient
        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        // Check if patient already has an emergency contact
        if (emergencyContactRepository.existsByPatientId(patientId)) {
            throw new RuntimeException("Patient already has an emergency contact");
        }

        // Convert PatientEntity to Patient domain model
        Patient patient = new Patient();
        patient.setId(patientEntity.getId());
        patient.setFirstName(patientEntity.getFirstName());
        patient.setLastName(patientEntity.getLastName());

        // Create emergency contact
        EmergencyContact createdContact = emergencyContactService.createEmergencyContact(contact, patient);

        // Convert to entity and save
        EmergencyContactEntity entity = new EmergencyContactEntity();
        entity.setFirstName(createdContact.getFirstName());
        entity.setLastName(createdContact.getLastName());
        entity.setRelationship(createdContact.getRelationship());
        entity.setPhoneNumber(createdContact.getPhoneNumber());
        entity.setPatient(patientEntity);

        EmergencyContactEntity savedEntity = emergencyContactRepository.save(entity);

        // Convert back to domain model
        return convertToDomain(savedEntity, patient);
    }

    public EmergencyContact getEmergencyContactByPatientId(Long patientId) {
        Optional<EmergencyContactEntity> entity = emergencyContactRepository.findByPatientId(patientId);
        if (entity.isPresent()) {
            PatientEntity patientEntity = entity.get().getPatient();
            Patient patient = new Patient();
            patient.setId(patientEntity.getId());
            patient.setFirstName(patientEntity.getFirstName());
            patient.setLastName(patientEntity.getLastName());
            
            return convertToDomain(entity.get(), patient);
        }
        return null;
    }

    public EmergencyContact updateEmergencyContact(Long patientId, EmergencyContact contact) {
        EmergencyContactEntity existingEntity = emergencyContactRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Emergency contact not found for patient id: " + patientId));

        // Convert to domain model for update
        EmergencyContact existingContact = convertToDomain(existingEntity, null);
        EmergencyContact updatedContact = emergencyContactService.updateEmergencyContact(existingContact, contact);

        // Update entity
        existingEntity.setFirstName(updatedContact.getFirstName());
        existingEntity.setLastName(updatedContact.getLastName());
        existingEntity.setRelationship(updatedContact.getRelationship());
        existingEntity.setPhoneNumber(updatedContact.getPhoneNumber());

        EmergencyContactEntity savedEntity = emergencyContactRepository.save(existingEntity);

        // Convert back to domain model
        PatientEntity patientEntity = existingEntity.getPatient();
        Patient patient = new Patient();
        patient.setId(patientEntity.getId());
        patient.setFirstName(patientEntity.getFirstName());
        patient.setLastName(patientEntity.getLastName());

        return convertToDomain(savedEntity, patient);
    }

    private EmergencyContact convertToDomain(EmergencyContactEntity entity, Patient patient) {
        EmergencyContact contact = new EmergencyContact();
        contact.setId(entity.getId());
        contact.setFirstName(entity.getFirstName());
        contact.setLastName(entity.getLastName());
        contact.setRelationship(entity.getRelationship());
        contact.setPhoneNumber(entity.getPhoneNumber());
        contact.setPatient(patient);
        return contact;
    }
}