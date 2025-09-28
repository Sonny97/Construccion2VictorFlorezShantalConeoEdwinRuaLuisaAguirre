package app.domain.model;

import java.util.Date;

public class Visit {
    private Date visitDate;
    private User nurse;
    private int respiratoryRate;

    private int id;
    private long patientId;
    private long nurseId;
    private String bloodPressure;
    private double temperature;
    private int pulse;
    private double oxygenLevel;
    private String medications;
    private String procedures;
    private String observations;

    
    public void setId(int id) { this.id = id; }
    public void setPatientId(long patientId) { this.patientId = patientId; }
    public void setNurseId(long nurseId) { this.nurseId = nurseId; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public void setPulse(int pulse) { this.pulse = pulse; }
    public void setOxygenLevel(double oxygenLevel) { this.oxygenLevel = oxygenLevel; }
    public void setMedications(String medications) { this.medications = medications; }
    public void setProcedures(String procedures) { this.procedures = procedures; }
    public void setObservations(String observations) { this.observations = observations; }

    // Setters nuevos
    public void setVisitDate(Date visitDate) { this.visitDate = visitDate; }
    public void setNurse(User nurse) { this.nurse = nurse; }
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
    public User getNurse() { return nurse; }
    public int getRespiratoryRate() { return respiratoryRate; }
}
