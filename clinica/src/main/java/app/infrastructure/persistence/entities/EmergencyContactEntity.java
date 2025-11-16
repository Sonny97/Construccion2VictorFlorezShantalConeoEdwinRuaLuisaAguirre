package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "emergency_contacts")
public class EmergencyContactEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    
    @Column(nullable = false, length = 50)
    private String relationship;
    
    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private PatientEntity patient;

    // Constructors
    public EmergencyContactEntity() {}

    public EmergencyContactEntity(String firstName, String lastName, String relationship, String phoneNumber, PatientEntity patient) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.phoneNumber = phoneNumber;
        this.patient = patient;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) { this.relationship = relationship; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public PatientEntity getPatient() { return patient; }
    public void setPatient(PatientEntity patient) { this.patient = patient; }
}