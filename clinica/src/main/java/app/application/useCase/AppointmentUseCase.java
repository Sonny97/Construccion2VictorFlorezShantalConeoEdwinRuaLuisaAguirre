package app.application.usecase;

import app.adapter.rest.mapper.DoctorRestMapper;
import app.adapter.rest.mapper.PatientRestMapper;
import app.domain.model.Appointment;
import app.domain.model.Patient;
import app.domain.model.Doctor;
import app.infrastructure.persistence.entities.AppointmentEntity;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.entities.DoctorEntity;
import app.infrastructure.persistence.repository.AppointmentRepository;
import app.infrastructure.persistence.repository.PatientRepository;
import app.infrastructure.persistence.repository.DoctorRepository;
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
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorRestMapper doctorRestMapper;

    @Autowired
    private PatientRestMapper patientRestMapper;

    public Appointment createAppointment(Appointment appointment, Long patientId, Long doctorId) {
        // Find patient and doctor entities
        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
        
        DoctorEntity doctorEntity = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        // Convert entities to domain models
        Patient patient = convertPatientEntityToDomain(patientEntity);
        Doctor doctor = doctorRestMapper.toDomain(doctorEntity);

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStatus("SCHEDULED");

        // Convert to entity and save
        AppointmentEntity entity = new AppointmentEntity();
        entity.setAppointmentDate(appointment.getAppointmentDate());
        entity.setStatus(appointment.getStatus());
        entity.setReason(appointment.getReason());
        entity.setNotes(appointment.getNotes());
        entity.setPatient(patientEntity);
        entity.setDoctor(doctorEntity);

        AppointmentEntity savedEntity = appointmentRepository.save(entity);
        savedEntity.setPatient(patientEntity); // Ensure patient is set
        savedEntity.setDoctor(doctorEntity);   // Ensure doctor is set

        // Convert back to domain model
        return convertToDomain(savedEntity);
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        List<AppointmentEntity> entities = appointmentRepository.findByPatientId(patientId);
        return entities.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        List<AppointmentEntity> entities = appointmentRepository.findByDoctorId(doctorId);
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
        
        if (entity.getDoctor() != null) {
            appointment.setDoctor(doctorRestMapper.toDomain(entity.getDoctor()));
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

    // Método auxiliar para convertir Patient a PatientEntity si es necesario
    private PatientEntity convertPatientToEntity(Patient patient) {
        PatientEntity entity = new PatientEntity();
        entity.setId(patient.getId());
        entity.setFirstName(patient.getFirstName());
        entity.setLastName(patient.getLastName());
        entity.setDocumentId(patient.getDocumentId());
        entity.setBirthDate(patient.getBirthDate());
        entity.setGender(patient.getGender());
        entity.setAddress(patient.getAddress());
        entity.setPhoneNumber(patient.getPhoneNumber());
        entity.setEmergencyContact(patient.getEmergencyContact());
        entity.setAllergies(patient.getAllergies());
        entity.setMedicalConditions(patient.getMedicalConditions());
        return entity;
    }
}