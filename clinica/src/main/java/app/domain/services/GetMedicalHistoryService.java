package app.domain.services;

import app.domain.model.MedicalHistory;
import app.domain.model.MedicalRegister;
import app.domain.ports.MedicalHistoryPort;
import app.domain.ports.MedicalRegisterPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de dominio para obtener una Historia Clínica completa.
 * Incluye todos los registros médicos del paciente.
 */
@Service
public class GetMedicalHistoryService {
    
    @Autowired
    private MedicalHistoryPort medicalHistoryPort;
    
    @Autowired
    private MedicalRegisterPort medicalRegisterPort;

    /**
     * Obtiene una historia clínica por su ID
     * 
     * @param id El ID de la historia clínica
     * @return La historia clínica encontrada
     * @throws Exception Si no se encuentra la historia clínica
     */
    public MedicalHistory getById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("El ID es obligatorio");
        }
        
        MedicalHistory medicalHistory = medicalHistoryPort.findById(id);
        
        if (medicalHistory == null) {
            throw new Exception("Historia clínica no encontrada con ID: " + id);
        }
        
        // Cargar todos los registros médicos del paciente
        List<MedicalRegister> registers = medicalRegisterPort.findByPatientId(medicalHistory.getPatientId());
        medicalHistory.setMedicalRegisters(registers);
        
        return medicalHistory;
    }

    /**
     * Obtiene la historia clínica completa de un paciente por su ID
     * Incluye todos los registros médicos ordenados por fecha
     * 
     * @param patientId El ID del paciente
     * @return La historia clínica del paciente con todos sus registros
     * @throws Exception Si no se encuentra la historia clínica del paciente
     */
    public MedicalHistory getByPatientId(Long patientId) throws Exception {
        if (patientId == null) {
            throw new Exception("El ID del paciente es obligatorio");
        }
        
        MedicalHistory medicalHistory = medicalHistoryPort.findByPatientId(patientId);
        
        if (medicalHistory == null) {
            throw new Exception("No se encontró historia clínica para el paciente con ID: " + patientId);
        }
        
        // Cargar todos los registros médicos del paciente
        List<MedicalRegister> registers = medicalRegisterPort.findByPatientId(patientId);
        medicalHistory.setMedicalRegisters(registers);
        
        return medicalHistory;
    }
}
