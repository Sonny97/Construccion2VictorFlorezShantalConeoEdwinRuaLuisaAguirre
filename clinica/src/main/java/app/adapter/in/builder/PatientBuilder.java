package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;

import app.adapter.in.validators.UserValidator;
import app.domain.model.Employee;

public class PatientBuilder {
     @Autowired
    private UserValidator userValidator;

    public Employee build(String firstName, String lastName, String email, int documentId, String birthDate, String gender,
            String address, int phoneNumber) throws Exception {
        Employee employee = new Employee();
        employee.setFirstName(userValidator.nameValidator(firstName));
        employee.setLastName(userValidator.lastNameValidator(lastName));
        employee.setDocumentId(documentId);
        employee.setEmail(userValidator.emailValidator(email));
        employee.setGender(userValidator.genderValidator(gender));
        employee.setBirthDate(userValidator.birthDateValidator(birthDate));
        employee.setAddress(userValidator.addressValidator(address));
        employee.setPhoneNumber(phoneNumber); 
        return employee;
    }
}
