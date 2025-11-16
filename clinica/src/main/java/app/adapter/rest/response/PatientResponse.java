package app.adapter.rest.response;

import java.time.LocalDate;
import java.util.List;

public class PatientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Long documentId;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String phoneNumber;
    // private String emergencyContact;
    private String allergies;
    private String medicalConditions;
     // NUEVOS CAMPOS PARA RELACIONES
    private EmergencyContactResponse detailedEmergencyContact;
    private MedicalInsuranceResponse medicalInsurance;
    private List<AppointmentResponse> appointments; // ← NUEVO: Lista de citas

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }
    
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    // public String getEmergencyContact() { return emergencyContact; }
    // public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    
    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }
    
    public String getMedicalConditions() { return medicalConditions; }
    public void setMedicalConditions(String medicalConditions) { this.medicalConditions = medicalConditions; }

    // NUEVOS GETTERS Y SETTERS
    public EmergencyContactResponse getDetailedEmergencyContact() { return detailedEmergencyContact; }
    public void setDetailedEmergencyContact(EmergencyContactResponse detailedEmergencyContact) { this.detailedEmergencyContact = detailedEmergencyContact; }
    public MedicalInsuranceResponse getMedicalInsurance() { return medicalInsurance; }
    public void setMedicalInsurance(MedicalInsuranceResponse medicalInsurance) { this.medicalInsurance = medicalInsurance; }
    public List<AppointmentResponse> getAppointments() { return appointments; }
    public void setAppointments(List<AppointmentResponse> appointments) { this.appointments = appointments; }
}