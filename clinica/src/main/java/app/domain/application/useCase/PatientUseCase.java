package app.domain.application.useCase;

import app.domain.model.Patient;
import app.domain.services.CreatePatient;
import app.domain.services.UpdatePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Period;
import java.time.LocalDate;

import java.util.List;

@Component
public class PatientUseCase {
    
    private final CreatePatient createPatientService;
    private final UpdatePatient updatePatientService;

    @Autowired
    public PatientUseCase(CreatePatient createPatientService, UpdatePatient updatePatientService) {
        this.createPatientService = createPatientService;
        this.updatePatientService = updatePatientService;
    }

    /**
     * Registra un nuevo paciente en el sistema
     * @param patient Datos del paciente a registrar
     * @return El paciente registrado con ID generado
     */
    public Patient registerPatient(Patient patient) {
        // Validaciones adicionales podrían ir aquí
        validatePatientData(patient);
        return createPatientService.registerPatient(patient);
    }

    /**
     * Actualiza los datos de un paciente existente
     * @param patient Datos actualizados del paciente
     * @throws Exception Si el paciente no existe o hay errores en la actualización
     */
    public void updatePatient(Patient patient) throws Exception {
        validatePatientData(patient);
        updatePatientService.update(patient);
    }

    /**
     * Busca un paciente por su número de identificación
     * @param idNumber Número de identificación del paciente
     * @return El paciente encontrado o null si no existe
     */
    public Patient findPatientByIdNumber(Long idNumber) {
        return createPatientService.findByIdNumber(idNumber);
    }

    /**
     * Busca pacientes por nombre completo (coincidencia parcial)
     * @param fullName Nombre completo o parte del mismo
     * @return Lista de pacientes que coinciden con la búsqueda
     */
    public List<Patient> findPatientsByFullName(String fullName) {
        return createPatientService.findByFullName(fullName);
    }

    /**
     * Valida los datos básicos del paciente
     * @param patient Paciente a validar
     * @throws IllegalArgumentException Si los datos no son válidos
     */
    private void validatePatientData(Patient patient) {
        if (patient.getFirstName() == null || patient.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente es requerido");
        }
        
        if (patient.getLastName() == null || patient.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del paciente es requerido");
        }
        
        if (patient.getDocumentId() == null || patient.getDocumentId().trim().isEmpty()) {
            throw new IllegalArgumentException("El documento de identidad es requerido");
        }
        
        if (patient.getBirthDate() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es requerida");
        }
        
        if (patient.getBirthDate().isAfter(java.time.LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }
        
        if (patient.getPhoneNumber() == null || patient.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono es requerido");
        }
    }

    /**
     * Obtiene la edad del paciente en años
     * @param patient Paciente del cual se quiere obtener la edad
     * @return Edad en años
     */
    public int calculatePatientAge(Patient patient) {
        if (patient.getBirthDate() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es requerida para calcular la edad");
        }
        
        LocalDate now = LocalDate.now();
        return Period.between(patient.getBirthDate(), now).getYears();
    }

    /**
     * Verifica si un paciente es mayor de edad
     * @param patient Paciente a verificar
     * @return true si es mayor de edad, false en caso contrario
     */
    public boolean isAdultPatient(Patient patient) {
        return calculatePatientAge(patient) >= 18;
    }
}