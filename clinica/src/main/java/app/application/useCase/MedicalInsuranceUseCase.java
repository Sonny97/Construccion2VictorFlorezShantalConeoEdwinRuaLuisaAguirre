package app.application.usecase;

import app.domain.model.MedicalInsurance;
import app.domain.model.Patient;
import app.domain.services.MedicalInsuranceService;
import app.infrastructure.persistence.entities.MedicalInsuranceEntity;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.repository.MedicalInsuranceRepository;
import app.infrastructure.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class MedicalInsuranceUseCase {

    @Autowired
    private MedicalInsuranceService medicalInsuranceService;

    @Autowired
    private MedicalInsuranceRepository medicalInsuranceRepository;

    @Autowired
    private PatientRepository patientRepository;

    public MedicalInsurance createMedicalInsurance(Long patientId, MedicalInsurance insurance) {
        // Find patient
        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        // Check if patient already has medical insurance
        if (medicalInsuranceRepository.existsByPatientId(patientId)) {
            throw new RuntimeException("Patient already has medical insurance");
        }

        // Convert PatientEntity to Patient domain model
        Patient patient = new Patient();
        patient.setId(patientEntity.getId());
        patient.setFirstName(patientEntity.getFirstName());
        patient.setLastName(patientEntity.getLastName());

        // Create medical insurance
        MedicalInsurance createdInsurance = medicalInsuranceService.createMedicalInsurance(insurance, patient);

        // Convert to entity and save
        MedicalInsuranceEntity entity = new MedicalInsuranceEntity();
        entity.setCompanyName(createdInsurance.getCompanyName());
        entity.setPolicyNumber(createdInsurance.getPolicyNumber());
        entity.setIsPolicyActive(createdInsurance.getIsPolicyActive());
        entity.setPolicyExpiryDate(createdInsurance.getPolicyExpiryDate());
        entity.setPatient(patientEntity);

        MedicalInsuranceEntity savedEntity = medicalInsuranceRepository.save(entity);

        // Convert back to domain model
        return convertToDomain(savedEntity, patient);
    }

    public MedicalInsurance getMedicalInsuranceByPatientId(Long patientId) {
        Optional<MedicalInsuranceEntity> entity = medicalInsuranceRepository.findByPatientId(patientId);
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

    public MedicalInsurance updateMedicalInsurance(Long patientId, MedicalInsurance insurance) {
        MedicalInsuranceEntity existingEntity = medicalInsuranceRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Medical insurance not found for patient id: " + patientId));

        // Convert to domain model for update
        MedicalInsurance existingInsurance = convertToDomain(existingEntity, null);
        MedicalInsurance updatedInsurance = medicalInsuranceService.updateMedicalInsurance(existingInsurance, insurance);

        // Update entity
        existingEntity.setCompanyName(updatedInsurance.getCompanyName());
        existingEntity.setPolicyNumber(updatedInsurance.getPolicyNumber());
        existingEntity.setIsPolicyActive(updatedInsurance.getIsPolicyActive());
        existingEntity.setPolicyExpiryDate(updatedInsurance.getPolicyExpiryDate());

        MedicalInsuranceEntity savedEntity = medicalInsuranceRepository.save(existingEntity);

        // Convert back to domain model
        PatientEntity patientEntity = existingEntity.getPatient();
        Patient patient = new Patient();
        patient.setId(patientEntity.getId());
        patient.setFirstName(patientEntity.getFirstName());
        patient.setLastName(patientEntity.getLastName());

        return convertToDomain(savedEntity, patient);
    }

    private MedicalInsurance convertToDomain(MedicalInsuranceEntity entity, Patient patient) {
        MedicalInsurance insurance = new MedicalInsurance();
        insurance.setId(entity.getId());
        insurance.setCompanyName(entity.getCompanyName());
        insurance.setPolicyNumber(entity.getPolicyNumber());
        insurance.setIsPolicyActive(entity.getIsPolicyActive());
        insurance.setPolicyExpiryDate(entity.getPolicyExpiryDate());
        insurance.setPatient(patient);
        return insurance;
    }
}