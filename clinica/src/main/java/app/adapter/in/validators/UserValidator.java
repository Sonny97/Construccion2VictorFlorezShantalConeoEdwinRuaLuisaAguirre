package app.adapter.in.validators;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

@Component
public class UserValidator {

    // Patrones de validación
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{1,15}$");
    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{1,10}$");

    /**
     * Valida el nombre completo
     */
    public String validateFullName(String fullName) throws Exception {
        if (fullName == null || fullName.trim().isEmpty()) {
            System.out.println("El nombre completo es requerido ");
        }

        if (fullName.length() > 100) {
            System.out.println("El nombre completo no puede exceder 100 caracteres ");

        }

        return fullName.trim();
    }

    /**
     * Valida el número de cédula (debe ser único - esta validación se hace en BD)
     */
    public Long validateDocumentId(Long documentId) throws Exception {
        if (documentId == null) {
            throw new Exception("El número de cédula es requerido");
        }

        if (documentId.toString().length() < 5 || documentId.toString().length() > 20) {
            throw new Exception("El número de cédula debe tener entre 5 y 20 dígitos");
        }

        return documentId;
    }

    /**
     * Valida el correo electrónico
     */
    public String validateEmail(String email) throws Exception {
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("El correo electrónico es requerido");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new Exception("El formato del correo electrónico no es válido");
        }

        String domain = email.substring(email.indexOf('@') + 1);
        boolean validDomain = false;

        if (domain.equals(domain)) {
            validDomain = true;
    
        }else {
            throw new Exception("El dominio del correo electrónico no es válido");
        }

        return email.trim().toLowerCase();
    }

    /**
     * Valida el número de teléfono
     */
    public String validatePhoneNumber(String phoneNumber) throws Exception {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new Exception("El número de teléfono es requerido");
        }

        // Remover espacios, guiones, paréntesis
        String cleanPhone = phoneNumber.replaceAll("[\\s\\-\\(\\)]", "");

        if (!PHONE_PATTERN.matcher(cleanPhone).matches()) {
            throw new Exception("El número de teléfono debe contener entre 1 y 10 dígitos");
        }

        return cleanPhone;
    }

    /**
     * Valida la fecha de nacimiento
     */
    public LocalDate validateBirthDate(LocalDate birthDate) throws Exception {
        if (birthDate == null) {
            throw new Exception("La fecha de nacimiento es requerida");
        }

        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);

        if (birthDate.isAfter(today)) {
            throw new Exception("La fecha de nacimiento no puede ser futura");
        }

        if (age.getYears() > 150) {
            throw new Exception("La edad no puede ser mayor a 150 años");
        }

        if (age.getYears() < 1) {
            throw new Exception("La edad no puede ser menor a 1 año");
        }

        return birthDate;
    }

    /**
     * Valida la dirección
     */
    public String validateAddress(String address) throws Exception {
        if (address == null || address.trim().isEmpty()) {
            throw new Exception("La dirección es requerida");
        }

        if (address.length() > 30) {
            throw new Exception("La dirección no puede exceder 30 caracteres");
        }

        return address.trim();
    }

    /**
     * Valida el nombre de usuario
     */
    public String validateUsername(String username) throws Exception {
        if (username == null || username.trim().isEmpty()) {
            throw new Exception("El nombre de usuario es requerido");
        }

        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new Exception("El nombre de usuario debe contener solo letras y números, máximo 15 caracteres");
        }

        return username.trim();
    }

    /**
     * Valida la contraseña
     */
    public String validatePassword(String password) throws Exception {
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("La contraseña es requerida");
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new Exception(
                    "La contraseña debe incluir al menos: una mayúscula, un número, un carácter especial y tener mínimo 8 caracteres");
        }

        return password.trim();
    }

    /**
     * Validación completa del usuario
     */
    public void validateUser(String fullName, Long documentId, String email, String phoneNumber,
            LocalDate birthDate, String address, String username, String password) throws Exception {
        validateFullName(fullName);
        validateDocumentId(documentId);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        validateBirthDate(birthDate);
        validateAddress(address);
        validateUsername(username);
        validatePassword(password);
    }
}