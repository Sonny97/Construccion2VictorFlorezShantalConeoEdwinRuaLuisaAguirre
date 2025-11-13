package app.domain.model;

import java.time.LocalDateTime;

public class Appointment {
    private Long id;
    private LocalDateTime appointmentDate;
    private String status; // SCHEDULED, COMPLETED, CANCELLED
    private String reason;
    private String notes;
    private Patient patient;
    private Doctor doctor;

    // Constructors
    public Appointment() {}

    public Appointment(LocalDateTime appointmentDate, String status, String reason, Patient patient, Doctor doctor) {
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.reason = reason;
        this.patient = patient;
        this.doctor = doctor;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    // Business logic methods
    public boolean isCompleted() {
        return "COMPLETED".equals(status);
    }
    
    public boolean isScheduled() {
        return "SCHEDULED".equals(status);
    }
    
    public boolean isCancelled() {
        return "CANCELLED".equals(status);
    }
}