package app.domain.services;

import app.domain.model.Visit;
import app.infrastructure.persistence.entities.VisitEntity;
import app.infrastructure.persistence.mapper.VisitMapper;
import app.infrastructure.persistence.repository.VisitRepository;
import jakarta.transaction.Transactional;
import app.infrastructure.persistence.repository.UserRepository;
import app.infrastructure.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    // REGISTRAR VISITA
    public Visit registerVisit(Visit visit) {
        System.out.println("📝 VisitService - Registering visit for patient: " + visit.getPatientId());
        
        try {
            // Verificar que el paciente existe
            patientRepository.findById(visit.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + visit.getPatientId()));
            
            // Verificar que la enfermera existe
            userRepository.findById(visit.getNurseId())
                    .orElseThrow(() -> new RuntimeException("Enfermera no encontrada con ID: " + visit.getNurseId()));
            
            // Convertir a Entity y guardar
            VisitEntity visitEntity = VisitMapper.toEntity(visit);
            VisitEntity savedEntity = visitRepository.save(visitEntity);
            
            System.out.println("✅ Visit registered successfully - ID: " + savedEntity.getId());
            
            // Convertir de vuelta a Domain
            return VisitMapper.toDomain(savedEntity);
            
        } catch (Exception e) {
            System.out.println("❌ ERROR in registerVisit: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // OBTENER VISITAS DE UN PACIENTE
    public List<Visit> getVisitsByPatient(Long patientId) {
        System.out.println("📋 VisitService - Getting visits for patient: " + patientId);
        
        List<VisitEntity> visitEntities = visitRepository.findByPatientId(patientId);
        
        return visitEntities.stream()
                .map(VisitMapper::toDomain)
                .collect(Collectors.toList());
    }
}