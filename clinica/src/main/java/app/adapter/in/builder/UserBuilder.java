package app.adapter.in.builder;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.UserValidator;
import app.domain.model.User;

public class UserBuilder {

    @Autowired
    private UserValidator userValidator;

    public User build(String firstName, String lastName, String email, int documentId, String birthDate, String gender,
            String address, int phoneNumber, String userName, String password) throws Exception {
        User user = new User();
        user.setUserName(userValidator.userNameValidator(userName));
        user.setPassword(userValidator.passwordValidator(password));
        user.setFirstName(userValidator.nameValidator(firstName));
        user.setLastName(userValidator.lastNameValidator(lastName));
        user.setDocumentId(documentId);
        user.setEmail(userValidator.emailValidator(email));
        user.setGender(userValidator.genderValidator(gender));
        user.setBirthDate(userValidator.birthDateValidator(birthDate));
        user.setAddress(userValidator.addressValidator(address));
        user.setPhoneNumber(phoneNumber); 
        return user;
    }
}
