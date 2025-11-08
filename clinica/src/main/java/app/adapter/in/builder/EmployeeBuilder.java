package app.adapter.in.builder;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.UserValidator;
import app.domain.model.Employee;
import app.domain.model.emuns.Role;

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
            long documentId,
            LocalDate birthDate,
            String gender,
            String address,
            String phoneNumber,
            Role role) throws Exception {
        Employee employee = new Employee();
        employee.setUserName(userValidator.userNameValidator(userName));
        employee.setPassword(userValidator.passwordValidator(password));
        employee.setFirstName(userValidator.nameValidator(firstName));
        employee.setLastName(userValidator.lastNameValidator(lastName));
        employee.setEmail(userValidator.emailValidator(email));
        employee.setDocumentId(documentId);
        employee.setBirthDate(birthDate);
        employee.setGender(userValidator.genderValidator(gender));
        employee.setAddress(userValidator.addressValidator(address));
        employee.setPhoneNumber(phoneNumber);
        if (role != null) {
            employee.setRole(role);
        }
        return employee;
    }
}