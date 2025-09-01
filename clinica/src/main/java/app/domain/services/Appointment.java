package app.domain.model;

import java.time.LocalDateTime;

public class Appointment {

    private String appointmentId;
    private String patientId;
    private LocalDateTime dateTime;

    public Appointment(String appointmentId, String patientId, LocalDateTime dateTime) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.dateTime = dateTime;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}