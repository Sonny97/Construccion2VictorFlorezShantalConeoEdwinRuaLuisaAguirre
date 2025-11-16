package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private Long id;
    private Employee employee; // Referencia al empleado
    private String specialization;
    private String licenseNumber;
    private List<Appointment> appointments = new ArrayList<>();

    // Constructors
    public Doctor() {}

    public Doctor(Employee employee, String specialization, String licenseNumber) {
        this.employee = employee;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }

    // Delegated methods from Employee for convenience
    public String getFirstName() {
        return employee != null ? employee.getFirstName() : null;
    }
    
    public String getLastName() {
        return employee != null ? employee.getLastName() : null;
    }
    
    public String getFullName() {
        return employee != null ? employee.getFirstName() + " " + employee.getLastName() : null;
    }
    
    public String getEmail() {
        return employee != null ? employee.getEmail() : null;
    }
    
    public String getPhoneNumber() {
        return employee != null ? employee.getPhoneNumber() : null;
    }
    
    public String getUserName() {
        return employee != null ? employee.getUserName() : null;
    }
    
    public Long getDocumentId() {
        return employee != null ? employee.getDocumentId() : null;
    }
}