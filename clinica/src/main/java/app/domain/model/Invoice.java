package app.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private Long id;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private Double totalAmount;
    private Double copaymentAmount; // $50,000 if policy active
    private Double insuranceCoverage;
    private Double patientPayment;
    private String status; // PENDING, PAID, CANCELLED
    private Patient patient;
    private Appointment appointment;
    private List<MedicalOrder> medicalOrders = new ArrayList<>();

    // Constructors
    public Invoice() {}

    public Invoice(String invoiceNumber, LocalDate invoiceDate, Double totalAmount, 
                   Patient patient, Appointment appointment) {
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
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
    public List<MedicalOrder> getMedicalOrders() { return medicalOrders; }
    public void setMedicalOrders(List<MedicalOrder> medicalOrders) { this.medicalOrders = medicalOrders; }

    // Business logic methods
    public void calculatePayments(MedicalInsurance insurance, Double annualCopaymentTotal) {
        if (insurance != null && insurance.isPolicyValid()) {
            // Check if patient reached annual copayment cap (1,000,000 pesos)
            if (annualCopaymentTotal >= 1000000.0) {
                this.copaymentAmount = 0.0;
                this.insuranceCoverage = this.totalAmount;
                this.patientPayment = 0.0;
            } else {
                this.copaymentAmount = 50000.0; // Fixed copayment
                this.insuranceCoverage = this.totalAmount - this.copaymentAmount;
                this.patientPayment = this.copaymentAmount;
            }
        } else {
            // No insurance or inactive policy - patient pays full amount
            this.copaymentAmount = 0.0;
            this.insuranceCoverage = 0.0;
            this.patientPayment = this.totalAmount;
        }
    }
    
    public boolean isPaid() {
        return "PAID".equals(status);
    }
}