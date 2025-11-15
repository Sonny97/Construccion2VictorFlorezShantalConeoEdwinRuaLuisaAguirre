package app.domain.model;

import java.time.LocalDate;

public class Visit {
<<<<<<< HEAD

    private Long id;
    private Long patientId;
    private Long nurseId;
    private LocalDate date;
    private String reason;
    private String observations;

    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }
=======
    private Integer id;
    private long patientId;
    private long nurseId;
    private Date visitDate;  // ← Debe ser Date, no String
    private String bloodPressure;
    private double temperature;
    private int pulse;
    private int respiratoryRate;
    private double oxygenLevel;
    private String medications;
    private String procedures;
    private String observations;
>>>>>>> ecabbdda117d0ae81b100168f1a84e192646d5f1
    
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

<<<<<<< HEAD
    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getNurseId() {
        return nurseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public String getObservations() {
        return observations;
    }
=======
    // Setters nuevos
    public void setVisitDate(Date visitDate) { this.visitDate = visitDate; }
    public void setRespiratoryRate(int respiratoryRate) { this.respiratoryRate = respiratoryRate; }
    // Getters
    public int getId() { return id; }
    public long getPatientId() { return patientId; }
    public long getNurseId() { return nurseId; }
    public String getBloodPressure() { return bloodPressure; }
    public double getTemperature() { return temperature; }
    public int getPulse() { return pulse; }
    public double getOxygenLevel() { return oxygenLevel; }
    public String getMedications() { return medications; }
    public String getProcedures() { return procedures; }
    public String getObservations() { return observations; }

    // Getters nuevos
    public Date getVisitDate() { return visitDate; }
    public int getRespiratoryRate() { return respiratoryRate; }
>>>>>>> ecabbdda117d0ae81b100168f1a84e192646d5f1
}
