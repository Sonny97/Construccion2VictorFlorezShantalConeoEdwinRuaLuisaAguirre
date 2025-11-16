package app.adapter.rest.response;

import java.time.LocalDate;
import java.util.List;

public class InvoiceResponse {
    private Long id;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private Double totalAmount;
    private Double copaymentAmount;
    private Double insuranceCoverage;
    private Double patientPayment;
    private String status;
    private Long patientId;
    private String patientName;
    private Long appointmentId;
    private List<MedicalOrderResponse> medicalOrders;

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
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    public List<MedicalOrderResponse> getMedicalOrders() { return medicalOrders; }
    public void setMedicalOrders(List<MedicalOrderResponse> medicalOrders) { this.medicalOrders = medicalOrders; }
}