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
            long documentId,        
            LocalDate birthDate,    
            String gender,
            String address, 
            String phoneNumber
    ) throws Exception {
        Employee employee = new Employee();
        employee.setUserName(userValidator.userNameValidator(userName));
        employee.setPassword(userValidator.passwordValidator(password));
        employee.setFirstName(userValidator.nameValidator(firstName));
        employee.setLastName(userValidator.lastNameValidator(lastName));
        employee.setDocumentId(documentId);
        employee.setEmail(userValidator.emailValidator(email));
        employee.setGender(userValidator.genderValidator(gender));
        employee.setBirthDate(birthDate);
        employee.setAddress(userValidator.addressValidator(address));
        employee.setPhoneNumber(phoneNumber); 
        return employee;
    }
}
