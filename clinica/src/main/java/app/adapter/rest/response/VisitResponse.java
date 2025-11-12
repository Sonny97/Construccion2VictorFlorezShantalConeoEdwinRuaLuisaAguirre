package app.adapter.rest.response;

import java.util.Date;

public class VisitResponse {
    private Long id;
    private Long patientId;
    private Long nurseId;
    private Date visitDate;  // ← Cambiado de String date a Date visitDate
    private String bloodPressure;
    private Double temperature;
    private Integer pulse;
    private Integer respiratoryRate;
    private Double oxygenLevel;
    private String medications;
    private String procedures;
    private String observations;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    
    public Long getNurseId() { return nurseId; }
    public void setNurseId(Long nurseId) { this.nurseId = nurseId; }
    
    public Date getVisitDate() { return visitDate; }  // ← CORREGIDO
    public void setVisitDate(Date visitDate) { this.visitDate = visitDate; }
    
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    
    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    
    public Integer getPulse() { return pulse; }
    public void setPulse(Integer pulse) { this.pulse = pulse; }
    
    public Integer getRespiratoryRate() { return respiratoryRate; }
    public void setRespiratoryRate(Integer respiratoryRate) { this.respiratoryRate = respiratoryRate; }
    
    public Double getOxygenLevel() { return oxygenLevel; }
    public void setOxygenLevel(Double oxygenLevel) { this.oxygenLevel = oxygenLevel; }
    
    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }
    
    public String getProcedures() { return procedures; }
    public void setProcedures(String procedures) { this.procedures = procedures; }
    
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
}