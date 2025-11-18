package app.adapter.rest.request;

/**
 * Request DTO para crear una Historia Clínica.
 * Solo los médicos pueden crear historias clínicas.
 */
public class MedicalHistoryRequest {
    private Long patientId;
    private String patientName;
    private String bloodType;
    private String allergies;
    private String chronicDiseases;
    private String familyHistory;
    private String surgicalHistory;
    private String observations;

    // Constructors
    public MedicalHistoryRequest() {}

    public MedicalHistoryRequest(Long patientId, String patientName, String bloodType,
                                String allergies, String chronicDiseases, String familyHistory,
                                String surgicalHistory, String observations) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.chronicDiseases = chronicDiseases;
        this.familyHistory = familyHistory;
        this.surgicalHistory = surgicalHistory;
        this.observations = observations;
    }

    // Getters and Setters
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(String chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getSurgicalHistory() {
        return surgicalHistory;
    }

    public void setSurgicalHistory(String surgicalHistory) {
        this.surgicalHistory = surgicalHistory;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "MedicalHistoryRequest{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", allergies='" + allergies + '\'' +
                ", chronicDiseases='" + chronicDiseases + '\'' +
                '}';
    }
}
