package app.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.MedicalRegister;
import app.domain.services.CreateMedicalRegisterService;
import app.domain.services.ListMedicalRegisterService;
import app.domain.services.UpdateMedicalRegisterService;
import java.util.List;

@Service
public class MedicalRegisterUseCase {

    @Autowired
    private CreateMedicalRegisterService createMedicalRegisterService;

    @Autowired
    private ListMedicalRegisterService listMedicalRegisterService;

    @Autowired
    private UpdateMedicalRegisterService updateMedicalRegisterService;

    // Crear un nuevo registro médico
    public MedicalRegister createMedicalRegister(MedicalRegister medicalRegister) throws Exception {
        return createMedicalRegisterService.create(medicalRegister);
    }

    // Listar todos los registros médicos
    public List<MedicalRegister> listAllMedicalRegisters() throws Exception {
        return listMedicalRegisterService.listAll();
    }

    // Listar registros médicos por paciente
    public List<MedicalRegister> listMedicalRegistersByPatient(Long patientId) throws Exception {
        return listMedicalRegisterService.listByPatient(patientId);
    }

    // Listar registros médicos por médico
    public List<MedicalRegister> listMedicalRegistersByMedic(Long medicId) throws Exception {
        return listMedicalRegisterService.listByMedic(medicId);
    }

    // Obtener un registro médico por ID
    public MedicalRegister getMedicalRegisterById(Long id) throws Exception {
        return listMedicalRegisterService.findById(id);
    }

    // Actualizar un registro médico
    public MedicalRegister updateMedicalRegister(MedicalRegister medicalRegister) throws Exception {
        return updateMedicalRegisterService.update(medicalRegister);
    }
}
