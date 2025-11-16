package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visits")
public class VisitEntity {  
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private PatientEntity patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id", referencedColumnName = "id", nullable = false)
    private UserEntity nurse;
    
    @Column(name = "visit_date", nullable = false)
    private Date visitDate;
    
    @Column(name = "blood_pressure", length = 20)
    private String bloodPressure;
    
    @Column(nullable = false)
    private double temperature;
    
    @Column(nullable = false)
    private int pulse;
    
    @Column(name = "respiratory_rate")
    private int respiratoryRate;
    
    @Column(name = "oxygen_level")
    private double oxygenLevel;
    
    @Column(length = 500)
    private String medications;
    
    @Column(length = 500)
    private String procedures;
    
    @Column(length = 1000)
    private String observations;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public PatientEntity getPatient() { return patient; }
    public void setPatient(PatientEntity patient) { this.patient = patient; }
    
    public UserEntity getNurse() { return nurse; }
    public void setNurse(UserEntity nurse) { this.nurse = nurse; }
    
    public Date getVisitDate() { return visitDate; }
    public void setVisitDate(Date visitDate) { this.visitDate = visitDate; }
    
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    
    public int getPulse() { return pulse; }
    public void setPulse(int pulse) { this.pulse = pulse; }
    
    public int getRespiratoryRate() { return respiratoryRate; }
    public void setRespiratoryRate(int respiratoryRate) { this.respiratoryRate = respiratoryRate; }
    
    public double getOxygenLevel() { return oxygenLevel; }
    public void setOxygenLevel(double oxygenLevel) { this.oxygenLevel = oxygenLevel; }
    
    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }
    
    public String getProcedures() { return procedures; }
    public void setProcedures(String procedures) { this.procedures = procedures; }
    
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
}