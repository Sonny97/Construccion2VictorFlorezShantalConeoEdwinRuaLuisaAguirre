package app.domain.services;

import app.domain.model.MedicalHistory;
import app.domain.model.MedicalRegister;
import app.domain.ports.MedicalHistoryPort;
import app.domain.ports.MedicalRegisterPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio de dominio para crear una Historia Clínica.
 * Una historia clínica contiene todos los registros médicos de un paciente.
 */
@Service
public class CreateMedicalHistoryService {
    
    @Autowired
    private MedicalHistoryPort medicalHistoryPort;
    
    @Autowired
    private MedicalRegisterPort medicalRegisterPort;

    /**
     * Crea una nueva historia clínica para un paciente.
     * Verifica que el paciente no tenga ya una historia clínica existente.
     * 
     * @param medicalHistory La historia clínica a crear
     * @return La historia clínica creada con su ID
     * @throws Exception Si hay errores de validación o el paciente ya tiene historia clínica
     */
    public MedicalHistory create(MedicalHistory medicalHistory) throws Exception {
        // Validaciones
        if (medicalHistory.getPatientId() == null) {
            throw new Exception("El ID del paciente es obligatorio");
        }
        
        if (medicalHistory.getPatientName() == null || medicalHistory.getPatientName().isEmpty()) {
            throw new Exception("El nombre del paciente es obligatorio");
        }
        
        // Verificar si el paciente ya tiene una historia clínica
        if (medicalHistoryPort.existsByPatientId(medicalHistory.getPatientId())) {
            throw new Exception("El paciente con ID " + medicalHistory.getPatientId() + " ya tiene una historia clínica");
        }
        
        // Establecer fechas
        if (medicalHistory.getCreationDate() == null) {
            medicalHistory.setCreationDate(LocalDateTime.now());
        }
        
        if (medicalHistory.getLastUpdateDate() == null) {
            medicalHistory.setLastUpdateDate(LocalDateTime.now());
        }
        
        // Guardar
        return medicalHistoryPort.save(medicalHistory);
    }
}
