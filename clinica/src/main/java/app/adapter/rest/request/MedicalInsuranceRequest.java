package app.adapter.rest.request;

import java.time.LocalDate;

public class MedicalInsuranceRequest {
    private String companyName;
    private String policyNumber;
    private Boolean isPolicyActive;
    private LocalDate policyExpiryDate;

    // Getters and Setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
    public Boolean getIsPolicyActive() { return isPolicyActive; }
    public void setIsPolicyActive(Boolean isPolicyActive) { this.isPolicyActive = isPolicyActive; }
    public LocalDate getPolicyExpiryDate() { return policyExpiryDate; }
    public void setPolicyExpiryDate(LocalDate policyExpiryDate) { this.policyExpiryDate = policyExpiryDate; }
}