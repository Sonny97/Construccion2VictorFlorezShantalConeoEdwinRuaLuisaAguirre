package app.adapter.rest.request;

import java.time.LocalDateTime;

public class AppointmentRequest {
    private LocalDateTime appointmentDate;
    private String reason;
    private String notes;
    private Long patientId;
    private Long medicId; // Cambiar de doctorId a medicId

    // Getters and Setters
    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getMedicId() { return medicId; } // Cambiar aquí
    public void setMedicId(Long medicId) { this.medicId = medicId; } // Y aquí
}