package app.adapter.rest.response;

import java.time.LocalDate;

public class EmployeeResponse {
    private Long id;
    public String userName;
	public String password;
    private String firstName;
    private String lastName;
    private String email;
    private long documentId;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String phoneNumber;
    private String role;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;	}
    
	public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;	}

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

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }


}