package app.adapter.in.validators;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class UserValidator extends SimpleValidator {

    public String userNameValidator(String value) throws Exception {
        return stringValidator("nombre del usuario", value);
    }

    public String passwordValidator(String value) throws Exception {
        return stringValidator("contraseña", value);
    }

    public String nameValidator(String value) throws Exception {
        return stringValidator("nombre de la persona", value);
    }

    public String lastNameValidator(String value) throws Exception {
        return stringValidator("apellido de la persona", value);
    }

    public String emailValidator(String value) throws Exception {
        return stringValidator("correo electronico de la persona", value);
    }

    public long documentValidator(String value) throws Exception {
        return longValidator("el documento de la persona", value);
    }

    public LocalDate birthDateValidator(String value) throws Exception {
        return localDateValidator("fecha de nacimiento", value);
    }

    public String addressValidator(String value) throws Exception {
        return stringValidator("direccion  de la persona", value);
    }

    public String genderValidator(String value) throws Exception {
        return stringValidator("direccion  de la persona", value);
    }

    public String phoneValidator(String value) throws Exception {
        return stringValidator("número de teléfono", value);
    }

}
