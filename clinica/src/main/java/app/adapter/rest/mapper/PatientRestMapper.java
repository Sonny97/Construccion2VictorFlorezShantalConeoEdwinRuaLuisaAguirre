package app.adapter.rest.mapper;

import app.adapter.rest.request.PatientRequest;
import app.adapter.rest.response.PatientResponse;
import app.adapter.rest.response.EmergencyContactResponse;
import app.adapter.rest.response.MedicalInsuranceResponse;
import app.adapter.rest.response.AppointmentResponse;
import app.domain.model.Patient;
import app.domain.model.Appointment;
import app.domain.model.EmergencyContact;
import app.domain.model.MedicalInsurance;
import app.domain.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientRestMapper {

    @Autowired
    private EmergencyContactRestMapper emergencyContactRestMapper;

    @Autowired
    private MedicalInsuranceRestMapper medicalInsuranceRestMapper;

    @Autowired
    private AppointmentRestMapper appointmentRestMapper;

    public Patient toDomain(PatientRequest request) {
        if (request == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setDocumentId(request.getDocumentId());
        patient.setBirthDate(request.getBirthDate());
        patient.setGender(request.getGender());
        patient.setAddress(request.getAddress());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setEmergencyContact(request.getEmergencyContact());
        patient.setAllergies(request.getAllergies());
        patient.setMedicalConditions(request.getMedicalConditions());

        return patient;
    }

    public PatientResponse toResponse(Patient patient) {
        if (patient == null) {
            return null;
        }

        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setFirstName(patient.getFirstName());
        response.setLastName(patient.getLastName());
        response.setDocumentId(patient.getDocumentId());
        response.setBirthDate(patient.getBirthDate());
        response.setGender(patient.getGender());
        response.setAddress(patient.getAddress());
        response.setPhoneNumber(patient.getPhoneNumber());
        // response.setEmergencyContact(patient.getEmergencyContact());
        response.setAllergies(patient.getAllergies());
        response.setMedicalConditions(patient.getMedicalConditions());
        // Mapear contacto de emergencia detallado
        if (patient.getDetailedEmergencyContact() != null) {
            EmergencyContactResponse emergencyContactResponse = emergencyContactRestMapper
                    .toResponse(patient.getDetailedEmergencyContact());
            response.setDetailedEmergencyContact(emergencyContactResponse);
        }

        // Mapear seguro médico
        if (patient.getMedicalInsurance() != null) {
            MedicalInsuranceResponse insuranceResponse = medicalInsuranceRestMapper
                    .toResponse(patient.getMedicalInsurance());
            response.setMedicalInsurance(insuranceResponse);
        }

        if (patient.getAppointments() != null && !patient.getAppointments().isEmpty()) {
            List<AppointmentResponse> appointmentResponses = patient.getAppointments().stream()
                    .map(appointmentRestMapper::toResponse)
                    .collect(Collectors.toList());
            response.setAppointments(appointmentResponses);
        } else {
            // Si no hay citas cargadas, dejar como lista vacía en lugar de null
            response.setAppointments(new ArrayList<>());
        }

        return response;
    }
}