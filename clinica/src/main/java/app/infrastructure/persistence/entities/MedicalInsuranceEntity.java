package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_insurances")
public class MedicalInsuranceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "company_name", nullable = false, length = 200)
    private String companyName;
    
    @Column(name = "policy_number", nullable = false, length = 50)
    private String policyNumber;
    
    @Column(name = "is_policy_active", nullable = false)
    private Boolean isPolicyActive;
    
    @Column(name = "policy_expiry_date", nullable = false)
    private LocalDate policyExpiryDate;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private PatientEntity patient;

    // Constructors
    public MedicalInsuranceEntity() {}

    public MedicalInsuranceEntity(String companyName, String policyNumber, Boolean isPolicyActive, LocalDate policyExpiryDate, PatientEntity patient) {
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
    public PatientEntity getPatient() { return patient; }
    public void setPatient(PatientEntity patient) { this.patient = patient; }
}