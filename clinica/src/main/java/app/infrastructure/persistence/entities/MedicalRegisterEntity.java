package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_registers")
public class MedicalRegisterEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long patientId;
    
    @Column(nullable = false)
    private Long medicId;
    
    @Column(nullable = false)
    private LocalDateTime registerDate;
    
    @Column(nullable = false, length = 500)
    private String inquiryReason;
    
    @Column(length = 1000)
    private String symptoms;
    
    @Column(length = 1000)
    private String physicalExam;
    
    @Column(length = 1000)
    private String diagnosis;
    
    @Column(length = 1000)
    private String treatment;
    
    @Column(length = 2000)
    private String prescriptions;
    
    @Column(length = 2000)
    private String observations;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getMedicId() {
        return medicId;
    }

    public void setMedicId(Long medicId) {
        this.medicId = medicId;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getInquiryReason() {
        return inquiryReason;
    }

    public void setInquiryReason(String inquiryReason) {
        this.inquiryReason = inquiryReason;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPhysicalExam() {
        return physicalExam;
    }

    public void setPhysicalExam(String physicalExam) {
        this.physicalExam = physicalExam;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
