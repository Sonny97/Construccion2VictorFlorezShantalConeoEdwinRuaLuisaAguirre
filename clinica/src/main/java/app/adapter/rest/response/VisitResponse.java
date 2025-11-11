package app.adapter.rest.response;

public class VisitResponse {
    private long id;
    private long patientId;
    private long nurseId;
    private String date;
    private String reason;
    private String observations;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getPatientId() { return patientId; }
    public void setPatientId(long patientId) { this.patientId = patientId; }

    public long getNurseId() { return nurseId; }
    public void setNurseId(long nurseId) { this.nurseId = nurseId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
}

