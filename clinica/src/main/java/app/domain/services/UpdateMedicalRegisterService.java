package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.MedicalRegister;
import app.domain.ports.MedicalRegisterPort;

@Service
public class UpdateMedicalRegisterService {
    
    @Autowired
    private MedicalRegisterPort medicalRegisterPort;

    public MedicalRegister update(MedicalRegister medicalRegister) throws Exception {
        // Validar que el ID esté presente
        if (medicalRegister.getId() == null) {
            throw new Exception("El ID del registro médico es obligatorio para actualizar");
        }
        
        // Verificar que el registro existe
        MedicalRegister existingRegister = medicalRegisterPort.findById(medicalRegister.getId());
        if (existingRegister == null) {
            throw new Exception("No se encontró el registro médico con ID: " + medicalRegister.getId());
        }
        
        // Actualizar
        medicalRegisterPort.update(medicalRegister);
        return medicalRegister;
    }
}
