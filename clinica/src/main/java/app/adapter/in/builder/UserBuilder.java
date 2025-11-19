package app.adapter.in.builder;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.UserValidator;
import app.domain.model.Employee;

@Component
public class UserBuilder {

    @Autowired
    private UserValidator userValidator;

    public Employee build(
            String userName,        
            String password, 
            String firstName, 
            String lastName, 
            String email, 
            Long documentId,        
            LocalDate birthDate,    
            String gender,
            String address, 
            String phoneNumber
    ) throws Exception {
        Employee employee = new Employee();
        employee.setUserName(userValidator.validateUsername(userName));
        employee.setPassword(userValidator.validatePassword(password));
        employee.setFirstName(userValidator.validateUsername(firstName));
        employee.setLastName(userValidator.validateUsername(lastName));
        employee.setDocumentId(userValidator.validateDocumentId(documentId));
        employee.setEmail(userValidator.validateEmail(email));
        employee.setGender(gender);
        employee.setBirthDate(userValidator.validateBirthDate(birthDate));
        employee.setAddress(userValidator.validateAddress(address));
        employee.setPhoneNumber(phoneNumber); 
        return employee;
    }
}
