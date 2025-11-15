package app.adapter.rest.mapper;

import app.adapter.rest.request.AppointmentRequest;
import app.adapter.rest.response.AppointmentResponse;
import app.domain.model.Appointment;
import app.infrastructure.persistence.entities.AppointmentEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentRestMapper {

    public Appointment toDomain(AppointmentRequest request) {
        if (request == null) return null;
        
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus("SCHEDULED");
        appointment.setReason(request.getReason());
        appointment.setNotes(request.getNotes());
        return appointment;
    }

    public AppointmentResponse toResponse(Appointment appointment) {
        if (appointment == null) return null;
        
        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId());
        response.setAppointmentDate(appointment.getAppointmentDate());
        response.setStatus(appointment.getStatus());
        response.setReason(appointment.getReason());
        response.setNotes(appointment.getNotes());
        
        if (appointment.getPatient() != null) {
            response.setPatientId(appointment.getPatient().getId());
            response.setPatientName(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName());
        }
        
        if (appointment.getDoctor() != null) {
            response.setDoctorId(appointment.getDoctor().getId());
            response.setDoctorName(appointment.getDoctor().getFullName());
            response.setDoctorSpecialization(appointment.getDoctor().getSpecialization());
        }
        
        return response;
    }

    public AppointmentEntity toEntity(Appointment appointment) {
        if (appointment == null) return null;
        
        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(appointment.getId());
        entity.setAppointmentDate(appointment.getAppointmentDate());
        entity.setStatus(appointment.getStatus());
        entity.setReason(appointment.getReason());
        entity.setNotes(appointment.getNotes());
        return entity;
    }

    public Appointment toDomain(AppointmentEntity entity) {
        if (entity == null) return null;
        
        Appointment appointment = new Appointment();
        appointment.setId(entity.getId());
        appointment.setAppointmentDate(entity.getAppointmentDate());
        appointment.setStatus(entity.getStatus());
        appointment.setReason(entity.getReason());
        appointment.setNotes(entity.getNotes());
        return appointment;
    }
}