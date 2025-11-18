package app.domain.ports;

import app.domain.model.MedicalHistory;

/**
 * Puerto de salida para la persistencia de Historias Clínicas
 */
public interface MedicalHistoryPort {
    
    /**
     * Guarda una nueva historia clínica
     */
    MedicalHistory save(MedicalHistory medicalHistory);
    
    /**
     * Busca una historia clínica por ID
     */
    MedicalHistory findById(Long id);
    
    /**
     * Busca la historia clínica de un paciente específico
     */
    MedicalHistory findByPatientId(Long patientId);
    
    /**
     * Verifica si existe una historia clínica para un paciente
     */
    boolean existsByPatientId(Long patientId);
}
