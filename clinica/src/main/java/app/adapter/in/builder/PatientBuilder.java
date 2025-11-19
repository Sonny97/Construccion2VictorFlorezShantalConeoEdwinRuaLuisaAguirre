package app.adapter.in.builder;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import app.adapter.in.validators.UserValidator;
import app.domain.model.Employee;

public class PatientBuilder {
     @Autowired
    private UserValidator userValidator;

    public Employee build(String firstName, String lastName, String email, Long documentId, LocalDate birthDate, String gender,
            String address, String phoneNumber) throws Exception {
        Employee employee = new Employee();
        employee.setFirstName(userValidator.validateFullName(firstName));
        employee.setLastName(userValidator.validateFullName(lastName));
        employee.setDocumentId(userValidator.validateDocumentId(documentId));
        employee.setEmail(userValidator.validateEmail(email));
        employee.setGender(gender);
        employee.setBirthDate(userValidator.validateBirthDate(birthDate));
        employee.setAddress(userValidator.validateAddress(address));
        employee.setPhoneNumber(userValidator.validatePhoneNumber(phoneNumber)); 
        return employee;
    }
}
