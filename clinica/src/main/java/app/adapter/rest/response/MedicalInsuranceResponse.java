package app.adapter.rest.response;

import java.time.LocalDate;

public class MedicalInsuranceResponse {
    private Long id;
    private String companyName;
    private String policyNumber;
    private Boolean isPolicyActive;
    private LocalDate policyExpiryDate;
    private Long patientId;
    private Boolean isPolicyValid;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
    public Boolean getIsPolicyActive() { return isPolicyActive; }
    public void setIsPolicyActive(Boolean isPolicyActive) { this.isPolicyActive = isPolicyActive; }
    public LocalDate getPolicyExpiryDate() { return policyExpiryDate; }
    public void setPolicyExpiryDate(LocalDate policyExpiryDate) { this.policyExpiryDate = policyExpiryDate; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Boolean getIsPolicyValid() { return isPolicyValid; }
    public void setIsPolicyValid(Boolean isPolicyValid) { this.isPolicyValid = isPolicyValid; }
}