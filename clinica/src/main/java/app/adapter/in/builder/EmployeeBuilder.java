package app.adapter.in.builder;

import app.adapter.in.validators.UserValidator;
import app.domain.model.Employee;
import app.domain.model.emuns.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class EmployeeBuilder {

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
            String phoneNumber,
            Role role 
    ) throws Exception {
        
        // 🔥 APLICAR VALIDACIONES
        String validatedUsername = userValidator.validateUsername(userName);
        String validatedPassword = userValidator.validatePassword(password);
        String validatedFirstName = userValidator.validateFullName(firstName + " " + lastName).split(" ")[0];
        String validatedLastName = userValidator.validateFullName(firstName + " " + lastName).split(" ")[1];
        String validatedEmail = userValidator.validateEmail(email);
        Long validatedDocumentId = userValidator.validateDocumentId(documentId);
        LocalDate validatedBirthDate = userValidator.validateBirthDate(birthDate);
        String validatedAddress = userValidator.validateAddress(address);
        String validatedPhone = userValidator.validatePhoneNumber(phoneNumber);

        Employee employee = new Employee();
        employee.setUserName(validatedUsername);
        employee.setPassword(validatedPassword); 
        employee.setFirstName(validatedFirstName);
        employee.setLastName(validatedLastName);
        employee.setEmail(validatedEmail);
        employee.setDocumentId(validatedDocumentId);
        employee.setBirthDate(validatedBirthDate);
        employee.setGender(gender); 
        employee.setAddress(validatedAddress);
        employee.setPhoneNumber(validatedPhone);
        employee.setRole(role);
        
        return employee;
    }
}