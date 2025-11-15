package app.adapter.rest.mapper;

import app.adapter.rest.request.MedicalInsuranceRequest;
import app.adapter.rest.response.MedicalInsuranceResponse;
import app.domain.model.MedicalInsurance;
import app.infrastructure.persistence.entities.MedicalInsuranceEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicalInsuranceRestMapper {

    public MedicalInsurance toDomain(MedicalInsuranceRequest request) {
        if (request == null) return null;
        
        MedicalInsurance insurance = new MedicalInsurance();
        insurance.setCompanyName(request.getCompanyName());
        insurance.setPolicyNumber(request.getPolicyNumber());
        insurance.setIsPolicyActive(request.getIsPolicyActive());
        insurance.setPolicyExpiryDate(request.getPolicyExpiryDate());
        return insurance;
    }

    public MedicalInsuranceResponse toResponse(MedicalInsurance insurance) {
        if (insurance == null) return null;
        
        MedicalInsuranceResponse response = new MedicalInsuranceResponse();
        response.setId(insurance.getId());
        response.setCompanyName(insurance.getCompanyName());
        response.setPolicyNumber(insurance.getPolicyNumber());
        response.setIsPolicyActive(insurance.getIsPolicyActive());
        response.setPolicyExpiryDate(insurance.getPolicyExpiryDate());
        response.setIsPolicyValid(insurance.isPolicyValid());
        if (insurance.getPatient() != null) {
            response.setPatientId(insurance.getPatient().getId());
        }
        return response;
    }

    public MedicalInsuranceEntity toEntity(MedicalInsurance insurance) {
        if (insurance == null) return null;
        
        MedicalInsuranceEntity entity = new MedicalInsuranceEntity();
        entity.setId(insurance.getId());
        entity.setCompanyName(insurance.getCompanyName());
        entity.setPolicyNumber(insurance.getPolicyNumber());
        entity.setIsPolicyActive(insurance.getIsPolicyActive());
        entity.setPolicyExpiryDate(insurance.getPolicyExpiryDate());
        return entity;
    }

    public MedicalInsurance toDomain(MedicalInsuranceEntity entity) {
        if (entity == null) return null;
        
        MedicalInsurance insurance = new MedicalInsurance();
        insurance.setId(entity.getId());
        insurance.setCompanyName(entity.getCompanyName());
        insurance.setPolicyNumber(entity.getPolicyNumber());
        insurance.setIsPolicyActive(entity.getIsPolicyActive());
        insurance.setPolicyExpiryDate(entity.getPolicyExpiryDate());
        return insurance;
    }
}