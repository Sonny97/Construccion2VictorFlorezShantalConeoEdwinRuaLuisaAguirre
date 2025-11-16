package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.MedicalRegister;
import app.domain.ports.MedicalRegisterPort;
import java.time.LocalDateTime;

@Service
public class CreateMedicalRegisterService {
    
    @Autowired
    private MedicalRegisterPort medicalRegisterPort;

    public MedicalRegister create(MedicalRegister medicalRegister) throws Exception {
        // Validaciones
        if (medicalRegister.getPatientId() == null) {
            throw new Exception("El ID del paciente es obligatorio");
        }
        
        if (medicalRegister.getMedicId() == null) {
            throw new Exception("El ID del médico es obligatorio");
        }
        
        if (medicalRegister.getInquiryReason() == null || medicalRegister.getInquiryReason().isEmpty()) {
            throw new Exception("El motivo de consulta es obligatorio");
        }
        
        if (medicalRegister.getDiagnosis() == null || medicalRegister.getDiagnosis().isEmpty()) {
            throw new Exception("El diagnóstico es obligatorio");
        }
        
        // Establecer fecha si no existe
        if (medicalRegister.getRegisterDate() == null) {
            medicalRegister.setRegisterDate(LocalDateTime.now());
        }
        
        // Guardar
        medicalRegisterPort.save(medicalRegister);
        return medicalRegister;
    }
}
