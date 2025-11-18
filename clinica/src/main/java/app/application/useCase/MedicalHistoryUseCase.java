package app.application.usecase;

import app.domain.model.MedicalHistory;

/**
 * Caso de uso para gestionar las Historias Clínicas.
 * Solo los médicos tienen acceso a estas operaciones.
 */
public interface MedicalHistoryUseCase {
    
    /**
     * Crea una nueva historia clínica para un paciente.
     * Solo puede ser ejecutada por médicos.
     * 
     * @param medicalHistory La historia clínica a crear
     * @return La historia clínica creada con su ID asignado
     */
    MedicalHistory createMedicalHistory(MedicalHistory medicalHistory);
    
    /**
     * Obtiene la historia clínica completa de un paciente,
     * incluyendo todos sus registros médicos.
     * Solo puede ser ejecutada por médicos.
     * 
     * @param patientId El ID del paciente
     * @return La historia clínica del paciente con todos sus registros
     */
    MedicalHistory getMedicalHistoryByPatientId(Long patientId);
    
    /**
     * Obtiene una historia clínica por su ID.
     * Solo puede ser ejecutada por médicos.
     * 
     * @param id El ID de la historia clínica
     * @return La historia clínica encontrada
     */
    MedicalHistory getMedicalHistoryById(Long id);
}
