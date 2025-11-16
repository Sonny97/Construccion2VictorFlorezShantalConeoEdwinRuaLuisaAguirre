package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.MedicalRegister;
import app.domain.ports.MedicalRegisterPort;
import java.util.List;

@Service
public class ListMedicalRegisterService {
    
    @Autowired
    private MedicalRegisterPort medicalRegisterPort;

    public List<MedicalRegister> listAll() throws Exception {
        return medicalRegisterPort.findAll();
    }
    
    public List<MedicalRegister> listByPatient(Long patientId) throws Exception {
        if (patientId == null) {
            throw new Exception("El ID del paciente es obligatorio");
        }
        return medicalRegisterPort.findByPatientId(patientId);
    }
    
    public List<MedicalRegister> listByMedic(Long medicId) throws Exception {
        if (medicId == null) {
            throw new Exception("El ID del médico es obligatorio");
        }
        return medicalRegisterPort.findByMedicId(medicId);
    }
    
    public MedicalRegister findById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("El ID del registro médico es obligatorio");
        }
        MedicalRegister register = medicalRegisterPort.findById(id);
        if (register == null) {
            throw new Exception("No se encontró el registro médico con ID: " + id);
        }
        return register;
    }
}
