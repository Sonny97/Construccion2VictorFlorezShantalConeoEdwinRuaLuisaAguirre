package app.domain.services;

import app.domain.model.Patient;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePatient {

    @Autowired
    private PatientRepository patientRepository;

    public void update(Patient patient) throws Exception {
        System.out.println("🔄 Updating patient - ID: " + patient.getId());
        
        // Verificar que el paciente existe - retorna PatientEntity
        PatientEntity existingPatientEntity = patientRepository.findById(patient.getId())
                .orElseThrow(() -> new Exception("Paciente no encontrado con ID: " + patient.getId()));
        
        // Actualizar campos en la ENTITY
        existingPatientEntity.setFirstName(patient.getFirstName());
        existingPatientEntity.setLastName(patient.getLastName());
        existingPatientEntity.setDocumentId(patient.getDocumentId());
        existingPatientEntity.setBirthDate(patient.getBirthDate());
        existingPatientEntity.setGender(patient.getGender());
        existingPatientEntity.setAddress(patient.getAddress());
        existingPatientEntity.setPhoneNumber(patient.getPhoneNumber());
        existingPatientEntity.setEmergencyContact(patient.getEmergencyContact());
        existingPatientEntity.setAllergies(patient.getAllergies());
        existingPatientEntity.setMedicalConditions(patient.getMedicalConditions());
        
        // Guardar la ENTITY
        patientRepository.save(existingPatientEntity);
        System.out.println("✅ Patient updated successfully - ID: " + patient.getId());
    }
}