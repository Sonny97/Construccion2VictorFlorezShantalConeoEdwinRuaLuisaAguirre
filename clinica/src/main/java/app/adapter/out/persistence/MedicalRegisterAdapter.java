package app.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.MedicalRegister;
import app.domain.ports.MedicalRegisterPort;
import app.infrastructure.persistence.entities.MedicalRegisterEntity;
import app.infrastructure.persistence.mapper.MedicalRegisterMapper;
import app.infrastructure.persistence.repository.MedicalRegisterRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRegisterAdapter implements MedicalRegisterPort {

    @Autowired
    private MedicalRegisterRepository medicalRegisterRepository;

    @Override
    public MedicalRegister findById(Long id) throws Exception {
        MedicalRegisterEntity entity = medicalRegisterRepository.findById(id).orElse(null);
        return entity != null ? MedicalRegisterMapper.toDomain(entity) : null;
    }

    @Override
    public List<MedicalRegister> findByPatientId(Long patientId) throws Exception {
        List<MedicalRegisterEntity> entities = medicalRegisterRepository.findByPatientIdOrderByRegisterDateDesc(patientId);
        return entities.stream()
                .map(MedicalRegisterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRegister> findByMedicId(Long medicId) throws Exception {
        List<MedicalRegisterEntity> entities = medicalRegisterRepository.findByMedicIdOrderByRegisterDateDesc(medicId);
        return entities.stream()
                .map(MedicalRegisterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRegister> findAll() throws Exception {
        List<MedicalRegisterEntity> entities = medicalRegisterRepository.findAll();
        return entities.stream()
                .map(MedicalRegisterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(MedicalRegister medicalRegister) throws Exception {
        MedicalRegisterEntity entity = MedicalRegisterMapper.toEntity(medicalRegister);
        MedicalRegisterEntity savedEntity = medicalRegisterRepository.save(entity);
        medicalRegister.setId(savedEntity.getId());
    }

    @Override
    public void update(MedicalRegister medicalRegister) throws Exception {
        if (medicalRegister.getId() == null) {
            throw new Exception("ID del registro médico es requerido para actualizar");
        }
        
        if (!medicalRegisterRepository.existsById(medicalRegister.getId())) {
            throw new Exception("Registro médico no encontrado con ID: " + medicalRegister.getId());
        }
        
        MedicalRegisterEntity entity = MedicalRegisterMapper.toEntity(medicalRegister);
        medicalRegisterRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID no puede ser nulo para eliminar");
        }
        
        if (!medicalRegisterRepository.existsById(id)) {
            throw new Exception("Registro médico no encontrado con ID: " + id);
        }
        
        medicalRegisterRepository.deleteById(id);
    }
}
