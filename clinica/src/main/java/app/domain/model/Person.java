package app.domain.model;

import java.time.LocalDate;

import app.domain.model.emuns.Role;

public class Person {
	private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long documentId;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private int phoneNumber;    
	private Role role;
	
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public long getDocumentId() { return documentId; }
    public void setDocumentId(long documentId) { this.documentId = documentId; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
    public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
	
	
	
