package app.domain.ports;

import app.domain.model.MedicalRegister;
import java.util.List;

public interface MedicalRegisterPort {
    MedicalRegister findById(Long id) throws Exception;
    List<MedicalRegister> findByPatientId(Long patientId) throws Exception;
    List<MedicalRegister> findByMedicId(Long medicId) throws Exception;
    List<MedicalRegister> findAll() throws Exception;
    void save(MedicalRegister medicalRegister) throws Exception;
    void update(MedicalRegister medicalRegister) throws Exception;
    void deleteById(Long id) throws Exception;
}
