package app.adapter.rest.mapper;

import org.springframework.stereotype.Component;
import app.adapter.rest.request.PatientRequest;
import app.adapter.rest.response.PatientResponse;
import app.domain.model.Patient;

@Component
public class PatientRestMapper {

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
        response.setEmergencyContact(patient.getEmergencyContact());
        response.setAllergies(patient.getAllergies());
        response.setMedicalConditions(patient.getMedicalConditions());
        
        return response;
    }
}