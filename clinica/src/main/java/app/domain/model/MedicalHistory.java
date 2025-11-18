package app.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de dominio que representa la Historia Clínica de un paciente.
 * Contiene todos los registros médicos, diagnósticos, tratamientos,
 * resultados de pruebas, prescripciones y demás información relevante
 * sobre la salud del paciente.
 */
public class MedicalHistory {
    private Long id;
    private Long patientId;
    private String patientName;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    
    // Registros médicos asociados
    private List<MedicalRegister> medicalRegisters;
    
    // Información agregada de la historia clínica
    private String bloodType;
    private String allergies;
    private String chronicDiseases;
    private String familyHistory;
    private String surgicalHistory;
    private String observations;

    // Constructors
    public MedicalHistory() {
        this.medicalRegisters = new ArrayList<>();
        this.creationDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
    }

    public MedicalHistory(Long patientId, String patientName, String bloodType, 
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
        this.medicalRegisters = new ArrayList<>();
        this.creationDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
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

    public List<MedicalRegister> getMedicalRegisters() {
        return medicalRegisters;
    }

    public void setMedicalRegisters(List<MedicalRegister> medicalRegisters) {
        this.medicalRegisters = medicalRegisters;
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

    // Métodos de utilidad
    public void addMedicalRegister(MedicalRegister register) {
        this.medicalRegisters.add(register);
        this.lastUpdateDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", medicalRegistersCount=" + (medicalRegisters != null ? medicalRegisters.size() : 0) +
                ", bloodType='" + bloodType + '\'' +
                ", allergies='" + allergies + '\'' +
                ", chronicDiseases='" + chronicDiseases + '\'' +
                '}';
    }
}
