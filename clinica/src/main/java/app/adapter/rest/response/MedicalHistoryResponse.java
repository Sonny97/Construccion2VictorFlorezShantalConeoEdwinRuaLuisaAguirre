package app.adapter.rest.response;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Response DTO para retornar información de una Historia Clínica.
 * Incluye todos los registros médicos asociados al paciente.
 */
public class MedicalHistoryResponse {
    private Long id;
    private Long patientId;
    private String patientName;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    
    // Información médica general
    private String bloodType;
    private String allergies;
    private String chronicDiseases;
    private String familyHistory;
    private String surgicalHistory;
    private String observations;
    
    // Registros médicos asociados
    private List<MedicalRegisterResponse> medicalRegisters;
    private int totalRegisters;

    // Constructors
    public MedicalHistoryResponse() {}

    public MedicalHistoryResponse(Long id, Long patientId, String patientName,
                                 LocalDateTime creationDate, LocalDateTime lastUpdateDate,
                                 String bloodType, String allergies, String chronicDiseases,
                                 String familyHistory, String surgicalHistory, String observations,
                                 List<MedicalRegisterResponse> medicalRegisters) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.chronicDiseases = chronicDiseases;
        this.familyHistory = familyHistory;
        this.surgicalHistory = surgicalHistory;
        this.observations = observations;
        this.medicalRegisters = medicalRegisters;
        this.totalRegisters = medicalRegisters != null ? medicalRegisters.size() : 0;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

    public List<MedicalRegisterResponse> getMedicalRegisters() {
        return medicalRegisters;
    }

    public void setMedicalRegisters(List<MedicalRegisterResponse> medicalRegisters) {
        this.medicalRegisters = medicalRegisters;
        this.totalRegisters = medicalRegisters != null ? medicalRegisters.size() : 0;
    }

    public int getTotalRegisters() {
        return totalRegisters;
    }

    public void setTotalRegisters(int totalRegisters) {
        this.totalRegisters = totalRegisters;
    }

    @Override
    public String toString() {
        return "MedicalHistoryResponse{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", totalRegisters=" + totalRegisters +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }
}
