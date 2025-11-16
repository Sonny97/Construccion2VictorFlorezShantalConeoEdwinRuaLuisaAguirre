package app.application.usecase;

import app.domain.model.Appointment;
import app.domain.model.Patient;
import app.domain.model.Employee;
import app.domain.model.emuns.Role;
import app.infrastructure.persistence.entities.AppointmentEntity;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.entities.UserEntity;
import app.infrastructure.persistence.repository.AppointmentRepository;
import app.infrastructure.persistence.repository.PatientRepository;
import app.infrastructure.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentUseCase {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public Appointment createAppointment(Appointment appointment, Long patientId, Long medicId) {
        System.out.println("🎯 Creating appointment for patient: " + patientId + " with medic: " + medicId);
        
        // Find patient and medic entities
        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
        
        UserEntity medicEntity = userRepository.findById(medicId)
                .orElseThrow(() -> new RuntimeException("Medic not found with id: " + medicId));
        
        // Verify that the user is actually a medic
        if (!"MEDIC".equals(medicEntity.getRole())) {
            throw new RuntimeException("User with id " + medicId + " is not a medic");
        }

        // Convert entities to domain models
        Patient patient = convertPatientEntityToDomain(patientEntity);
        Employee medic = convertUserEntityToEmployee(medicEntity); // ← LLAMAR AL MÉTODO

        appointment.setPatient(patient);
        appointment.setMedic(medic);
        appointment.setStatus("SCHEDULED");

        // Convert to entity and save
        AppointmentEntity entity = new AppointmentEntity();
        entity.setAppointmentDate(appointment.getAppointmentDate());
        entity.setStatus(appointment.getStatus());
        entity.setReason(appointment.getReason());
        entity.setNotes(appointment.getNotes());
        entity.setPatient(patientEntity);
        entity.setMedic(medicEntity);

        AppointmentEntity savedEntity = appointmentRepository.save(entity);

        // Convert back to domain model
        return convertToDomain(savedEntity);
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        List<AppointmentEntity> entities = appointmentRepository.findByPatientId(patientId);
        return entities.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsByMedicId(Long medicId) {
        List<AppointmentEntity> entities = appointmentRepository.findByMedicId(medicId);
        return entities.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    public Appointment updateAppointmentStatus(Long appointmentId, String status) {
        AppointmentEntity entity = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        entity.setStatus(status);
        AppointmentEntity savedEntity = appointmentRepository.save(entity);

        return convertToDomain(savedEntity);
    }

    private Appointment convertToDomain(AppointmentEntity entity) {
        Appointment appointment = new Appointment();
        appointment.setId(entity.getId());
        appointment.setAppointmentDate(entity.getAppointmentDate());
        appointment.setStatus(entity.getStatus());
        appointment.setReason(entity.getReason());
        appointment.setNotes(entity.getNotes());
        
        if (entity.getPatient() != null) {
            appointment.setPatient(convertPatientEntityToDomain(entity.getPatient()));
        }
        
        if (entity.getMedic() != null) {
            appointment.setMedic(convertUserEntityToEmployee(entity.getMedic())); // ← LLAMAR AL MÉTODO
        }
        
        return appointment;
    }

    private Patient convertPatientEntityToDomain(PatientEntity entity) {
        Patient patient = new Patient();
        patient.setId(entity.getId());
        patient.setFirstName(entity.getFirstName());
        patient.setLastName(entity.getLastName());
        patient.setDocumentId(entity.getDocumentId());
        patient.setBirthDate(entity.getBirthDate());
        patient.setGender(entity.getGender());
        patient.setAddress(entity.getAddress());
        patient.setPhoneNumber(entity.getPhoneNumber());
        patient.setEmergencyContact(entity.getEmergencyContact());
        patient.setAllergies(entity.getAllergies());
        patient.setMedicalConditions(entity.getMedicalConditions());
        return patient;
    }

    // MÉTODO QUE FALTABA: Convertir UserEntity a Employee
    private Employee convertUserEntityToEmployee(UserEntity entity) {
        if (entity == null) return null;
        
        Employee employee = new Employee();
        employee.setId(entity.getId());
        employee.setUserName(entity.getUserName());
        employee.setPassword(entity.getPassword());
        employee.setFirstName(entity.getFirstName());
        employee.setLastName(entity.getLastName());
        employee.setEmail(entity.getEmail());
        employee.setDocumentId(entity.getDocumentId());
        employee.setBirthDate(entity.getBirthDate());
        employee.setGender(entity.getGender());
        employee.setAddress(entity.getAddress());
        employee.setPhoneNumber(entity.getPhoneNumber());
        
        // Convert String role to Enum Role
        if (entity.getRole() != null) {
            try {
                employee.setRole(Role.valueOf(entity.getRole()));
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Unknown role: " + entity.getRole());
            }
        }
        
        return employee;
    }
}