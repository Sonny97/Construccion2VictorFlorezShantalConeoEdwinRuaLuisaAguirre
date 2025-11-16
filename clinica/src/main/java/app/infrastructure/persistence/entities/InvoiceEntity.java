package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
public class InvoiceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "invoice_number", unique = true, nullable = false, length = 50)
    private String invoiceNumber;
    
    @Column(name = "invoice_date", nullable = false)
    private LocalDate invoiceDate;
    
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;
    
    @Column(name = "copayment_amount")
    private Double copaymentAmount;
    
    @Column(name = "insurance_coverage")
    private Double insuranceCoverage;
    
    @Column(name = "patient_payment")
    private Double patientPayment;
    
    @Column(nullable = false, length = 20)
    private String status; // PENDING, PAID, CANCELLED
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private AppointmentEntity appointment;
    
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicalOrderEntity> medicalOrders = new ArrayList<>();

    // Constructors
    public InvoiceEntity() {}

    public InvoiceEntity(String invoiceNumber, LocalDate invoiceDate, Double totalAmount, 
                        PatientEntity patient, AppointmentEntity appointment) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.patient = patient;
        this.appointment = appointment;
        this.status = "PENDING";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public Double getCopaymentAmount() { return copaymentAmount; }
    public void setCopaymentAmount(Double copaymentAmount) { this.copaymentAmount = copaymentAmount; }
    public Double getInsuranceCoverage() { return insuranceCoverage; }
    public void setInsuranceCoverage(Double insuranceCoverage) { this.insuranceCoverage = insuranceCoverage; }
    public Double getPatientPayment() { return patientPayment; }
    public void setPatientPayment(Double patientPayment) { this.patientPayment = patientPayment; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public PatientEntity getPatient() { return patient; }
    public void setPatient(PatientEntity patient) { this.patient = patient; }
    public AppointmentEntity getAppointment() { return appointment; }
    public void setAppointment(AppointmentEntity appointment) { this.appointment = appointment; }
    public List<MedicalOrderEntity> getMedicalOrders() { return medicalOrders; }
    public void setMedicalOrders(List<MedicalOrderEntity> medicalOrders) { this.medicalOrders = medicalOrders; }
}