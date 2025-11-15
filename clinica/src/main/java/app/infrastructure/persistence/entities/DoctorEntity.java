package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
public class DoctorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;
    
    @Column(nullable = false, length = 100)
    private String specialization;
    
    @Column(name = "license_number", unique = true, nullable = false, length = 50)
    private String licenseNumber;
    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AppointmentEntity> appointments = new ArrayList<>();

    // Constructors
    public DoctorEntity() {}

    public DoctorEntity(UserEntity user, String specialization, String licenseNumber) {
        this.user = user;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public List<AppointmentEntity> getAppointments() { return appointments; }
    public void setAppointments(List<AppointmentEntity> appointments) { this.appointments = appointments; }
}