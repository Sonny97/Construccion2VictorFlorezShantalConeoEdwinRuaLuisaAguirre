package app.domain.model;

import java.time.LocalDate;

public class MedicalInsurance {
    private Long id;
    private String companyName;
    private String policyNumber;
    private Boolean isPolicyActive;
    private LocalDate policyExpiryDate;
    private Patient patient;

    // Constructors
    public MedicalInsurance() {}

    public MedicalInsurance(String companyName, String policyNumber, Boolean isPolicyActive, LocalDate policyExpiryDate, Patient patient) {
        this.companyName = companyName;
        this.policyNumber = policyNumber;
        this.isPolicyActive = isPolicyActive;
        this.policyExpiryDate = policyExpiryDate;
        this.patient = patient;
    }

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
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    // Business logic methods
    public boolean isPolicyValid() {
        return Boolean.TRUE.equals(isPolicyActive) && 
               policyExpiryDate != null && 
               policyExpiryDate.isAfter(LocalDate.now());
    }
}