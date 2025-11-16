package app.adapter.rest.mapper;

import org.springframework.stereotype.Component;
import app.adapter.rest.request.MedicalRegisterRequest;
import app.adapter.rest.response.MedicalRegisterResponse;
import app.domain.model.MedicalRegister;

@Component
public class MedicalRegisterRestMapper {

    public MedicalRegister toDomain(MedicalRegisterRequest request) {
        if (request == null) return null;
        
        MedicalRegister medicalRegister = new MedicalRegister();
        medicalRegister.setPatientId(request.getPatientId());
        medicalRegister.setMedicId(request.getMedicId());
        medicalRegister.setRegisterDate(request.getRegisterDate());
        medicalRegister.setInquiryReason(request.getInquiryReason());
        medicalRegister.setSymptoms(request.getSymptoms());
        medicalRegister.setPhysicalExam(request.getPhysicalExam());
        medicalRegister.setDiagnosis(request.getDiagnosis());
        medicalRegister.setTreatment(request.getTreatment());
        medicalRegister.setPrescriptions(request.getPrescriptions());
        medicalRegister.setObservations(request.getObservations());
        
        return medicalRegister;
    }

    public MedicalRegisterResponse toResponse(MedicalRegister medicalRegister) {
        if (medicalRegister == null) return null;
        
        MedicalRegisterResponse response = new MedicalRegisterResponse();
        response.setId(medicalRegister.getId());
        response.setPatientId(medicalRegister.getPatientId());
        response.setMedicId(medicalRegister.getMedicId());
        response.setRegisterDate(medicalRegister.getRegisterDate());
        response.setInquiryReason(medicalRegister.getInquiryReason());
        response.setSymptoms(medicalRegister.getSymptoms());
        response.setPhysicalExam(medicalRegister.getPhysicalExam());
        response.setDiagnosis(medicalRegister.getDiagnosis());
        response.setTreatment(medicalRegister.getTreatment());
        response.setPrescriptions(medicalRegister.getPrescriptions());
        response.setObservations(medicalRegister.getObservations());
        
        return response;
    }
}
