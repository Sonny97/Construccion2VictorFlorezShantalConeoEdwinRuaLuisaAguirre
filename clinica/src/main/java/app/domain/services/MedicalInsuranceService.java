package app.domain.services;

import app.domain.model.MedicalInsurance;
import app.domain.model.Patient;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class MedicalInsuranceService {

    public MedicalInsurance createMedicalInsurance(MedicalInsurance insurance, Patient patient) {
        validateMedicalInsurance(insurance);
        insurance.setPatient(patient);
        return insurance;
    }

    public MedicalInsurance updateMedicalInsurance(MedicalInsurance existingInsurance, MedicalInsurance updatedInsurance) {
        if (updatedInsurance.getCompanyName() != null) {
            existingInsurance.setCompanyName(updatedInsurance.getCompanyName());
        }
        if (updatedInsurance.getPolicyNumber() != null) {
            existingInsurance.setPolicyNumber(updatedInsurance.getPolicyNumber());
        }
        if (updatedInsurance.getIsPolicyActive() != null) {
            existingInsurance.setIsPolicyActive(updatedInsurance.getIsPolicyActive());
        }
        if (updatedInsurance.getPolicyExpiryDate() != null) {
            validatePolicyExpiryDate(updatedInsurance.getPolicyExpiryDate());
            existingInsurance.setPolicyExpiryDate(updatedInsurance.getPolicyExpiryDate());
        }
        return existingInsurance;
    }

    private void validateMedicalInsurance(MedicalInsurance insurance) {
        if (insurance.getCompanyName() == null || insurance.getCompanyName().trim().isEmpty()) {
            throw new IllegalArgumentException("Insurance company name is required");
        }
        if (insurance.getPolicyNumber() == null || insurance.getPolicyNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Policy number is required");
        }
        if (insurance.getIsPolicyActive() == null) {
            throw new IllegalArgumentException("Policy active status is required");
        }
        validatePolicyExpiryDate(insurance.getPolicyExpiryDate());
    }

    private void validatePolicyExpiryDate(LocalDate expiryDate) {
        if (expiryDate == null) {
            throw new IllegalArgumentException("Policy expiry date is required");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Policy expiry date cannot be in the past");
        }
    }

    public Double calculateAnnualCopaymentTotal(Double currentTotal, Double newCopayment) {
        return (currentTotal != null ? currentTotal : 0.0) + (newCopayment != null ? newCopayment : 0.0);
    }

    public boolean hasReachedCopaymentCap(Double annualCopaymentTotal) {
        return annualCopaymentTotal >= 1000000.0; // 1 million pesos cap
    }
}