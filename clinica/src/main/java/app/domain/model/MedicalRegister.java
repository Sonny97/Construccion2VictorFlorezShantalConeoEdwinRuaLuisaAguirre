package app.domain.model;

public class MedicalRegister {
    private final String date;
    private final String medicalIdNumber;
    private final String inquryReason;
    private final String medicalConditions;
    private final String diagnosis;

    public MedicalRegister(String date, String medicalIdNumber, String inquryReason, 
                         String medicalConditions, String diagnosis) {
        this.date = date;
        this.medicalIdNumber = medicalIdNumber;
        this.medicalConditions = medicalConditions;
        this.inquryReason = inquryReason;
        this.diagnosis = diagnosis;
    }

    public String getDate() { return date; }
    public String getMedicalIdNumber() { return medicalIdNumber; }
    public String getMedicalConditions() { return medicalConditions; }
    public String getInquryReason() { return inquryReason; }
    public String getDiagnosis() { return diagnosis; }
}