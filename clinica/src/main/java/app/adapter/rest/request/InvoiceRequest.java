package app.adapter.rest.request;

import java.time.LocalDate;
import java.util.List;

public class InvoiceRequest {
    private LocalDate invoiceDate;
    private Double totalAmount;
    private Long patientId;
    private Long appointmentId;
    private List<MedicalOrderRequest> medicalOrders;

    // Getters and Setters
    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    public List<MedicalOrderRequest> getMedicalOrders() { return medicalOrders; }
    public void setMedicalOrders(List<MedicalOrderRequest> medicalOrders) { this.medicalOrders = medicalOrders; }
}