package app.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @NotBlank(message = "La cédula es obligatoria")
    @Size(min = 6, max = 15, message = "La cédula debe tener entre 6 y 15 caracteres")
    private String idNumber;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate birthDate;

    @NotBlank(message = "El género es obligatorio")
    private String gender;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe tener 10 dígitos")
    private String phoneNumber;

    @Email(message = "El correo electrónico debe tener un formato válido")
    private String email;

    
}
