package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class AppointmentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;
    
    @Column(nullable = false, length = 20)
    private String status; // SCHEDULED, COMPLETED, CANCELLED
    
    @Column(length = 500)
    private String reason;
    
    @Column(length = 1000)
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false) // ← CAMBIAR A doctor_id
    private UserEntity medic; // Pero el campo Java sigue llamándose medic

    // Constructors
    public AppointmentEntity() {}

    public AppointmentEntity(LocalDateTime appointmentDate, String status, String reason, 
                           PatientEntity patient, UserEntity medic) {
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.reason = reason;
        this.patient = patient;
        this.medic = medic;
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
    public PatientEntity getPatient() { return patient; }
    public void setPatient(PatientEntity patient) { this.patient = patient; }
    public UserEntity getMedic() { return medic; }
    public void setMedic(UserEntity medic) { this.medic = medic; }
}