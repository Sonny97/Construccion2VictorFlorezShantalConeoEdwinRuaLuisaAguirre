package app.application.usecase;

import app.domain.model.Patient;
import app.domain.model.Appointment; // ← DEBE SER domain.model.Appointment
import app.domain.model.EmergencyContact;
import app.domain.model.MedicalInsurance;
import app.domain.services.CreatePatient;
import app.domain.services.UpdatePatient;
import app.infrastructure.persistence.repository.EmergencyContactRepository;
import app.infrastructure.persistence.repository.MedicalInsuranceRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Period;
import java.time.LocalDate;
import java.util.List;

@Component
public class PatientUseCase {

    private final CreatePatient createPatientService;
    private final UpdatePatient updatePatientService;

    private AppointmentUseCase appointmentUseCase;
    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Autowired
    private MedicalInsuranceRepository medicalInsuranceRepository;

    @Autowired
    public PatientUseCase(CreatePatient createPatientService, UpdatePatient updatePatientService,
            AppointmentUseCase appointmentUseCase,EmergencyContactRepository emergencyContactRepository,
                         MedicalInsuranceRepository medicalInsuranceRepository) {
        this.createPatientService = createPatientService;
        this.updatePatientService = updatePatientService;
        this.appointmentUseCase = appointmentUseCase;
         this.emergencyContactRepository = emergencyContactRepository;
        this.medicalInsuranceRepository = medicalInsuranceRepository;
         System.out.println("✅ PatientUseCase initialized with all dependencies");
    }

    /**
     * Registra un nuevo paciente en el sistema
     * 
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
     * 
     * @param patient Datos actualizados del paciente
     * @throws Exception Si el paciente no existe o hay errores en la actualización
     */
    public void updatePatient(Patient patient) throws Exception {
        validatePatientData(patient);
        updatePatientService.update(patient);
    }

    /**
     * Busca un paciente por su número de identificación
     * 
     * @param idNumber Número de identificación del paciente
     * @return El paciente encontrado o null si no existe
     */
    public Patient findPatientByIdNumber(Long idNumber) {
        System.out.println(
                "🔍 DEBUG - PatientUseCase initialized with appointmentUseCase: " + (appointmentUseCase != null));

        Patient patient = createPatientService.findByIdNumber(idNumber);

        if (patient != null) {
            loadPatientAppointments(patient);
        }

        return patient;
    }

    private void loadPatientRelations(Patient patient) {
        if (patient != null && patient.getId() != null) {
            System.out.println("🎯 DEBUG - loadPatientRelations START for patient ID: " + patient.getId());

            // Verificar que los repositorios no son null
            System.out.println("🔍 DEBUG - emergencyContactRepository: " + (emergencyContactRepository != null));
            System.out.println("🔍 DEBUG - medicalInsuranceRepository: " + (medicalInsuranceRepository != null));
            System.out.println("🔍 DEBUG - appointmentUseCase: " + (appointmentUseCase != null));

            // 1. Cargar contacto de emergencia detallado
            System.out.println("🔍 DEBUG - Loading emergency contact...");
            loadEmergencyContact(patient);

            // 2. Cargar seguro médico
            System.out.println("🔍 DEBUG - Loading medical insurance...");
            loadMedicalInsurance(patient);

            // 3. Cargar citas
            System.out.println("🔍 DEBUG - Loading appointments...");
            loadPatientAppointments(patient);

            System.out.println("🎯 DEBUG - loadPatientRelations END");
        }
    }

    private void loadEmergencyContact(Patient patient) {
        try {
            Optional<EmergencyContact> emergencyContact = emergencyContactRepository.findByPatientId(patient.getId())
                    .map(this::convertEmergencyContactEntityToDomain);

            if (emergencyContact.isPresent()) {
                patient.setDetailedEmergencyContact(emergencyContact.get());
                System.out.println("✅ Loaded detailed emergency contact");
            } else {
                System.out.println("ℹ️ No detailed emergency contact found");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error loading emergency contact: " + e.getMessage());
        }
    }

    private void loadMedicalInsurance(Patient patient) {
        try {
            Optional<MedicalInsurance> medicalInsurance = medicalInsuranceRepository.findByPatientId(patient.getId())
                    .map(this::convertMedicalInsuranceEntityToDomain);

            if (medicalInsurance.isPresent()) {
                patient.setMedicalInsurance(medicalInsurance.get());
                System.out.println("✅ Loaded medical insurance");
            } else {
                System.out.println("ℹ️ No medical insurance found");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error loading medical insurance: " + e.getMessage());
        }
    }

    private void loadPatientAppointments(Patient patient) {
        if (patient != null && patient.getId() != null && appointmentUseCase != null) {
            try {
                List<Appointment> appointments = appointmentUseCase.getAppointmentsByPatientId(patient.getId());
                patient.setAppointments(appointments);
                System.out.println("✅ Successfully loaded " + appointments.size() + " appointments");
            } catch (Exception e) {
                System.out.println("⚠️ Error loading appointments: " + e.getMessage());
            }
        }
    }

    private EmergencyContact convertEmergencyContactEntityToDomain(
            app.infrastructure.persistence.entities.EmergencyContactEntity entity) {
        EmergencyContact contact = new EmergencyContact();
        contact.setId(entity.getId());
        contact.setFirstName(entity.getFirstName());
        contact.setLastName(entity.getLastName());
        contact.setRelationship(entity.getRelationship());
        contact.setPhoneNumber(entity.getPhoneNumber());
        return contact;
    }

    /**
     * Convertir MedicalInsuranceEntity a MedicalInsurance domain
     */
    private MedicalInsurance convertMedicalInsuranceEntityToDomain(
            app.infrastructure.persistence.entities.MedicalInsuranceEntity entity) {
        MedicalInsurance insurance = new MedicalInsurance();
        insurance.setId(entity.getId());
        insurance.setCompanyName(entity.getCompanyName());
        insurance.setPolicyNumber(entity.getPolicyNumber());
        insurance.setIsPolicyActive(entity.getIsPolicyActive());
        insurance.setPolicyExpiryDate(entity.getPolicyExpiryDate());
        return insurance;
    }

    /**
     * Busca pacientes por nombre completo (coincidencia parcial)
     * 
     * @param fullName Nombre completo o parte del mismo
     * @return Lista de pacientes que coinciden con la búsqueda
     */
    public List<Patient> findPatientsByFullName(String fullName) {
        return createPatientService.findByFullName(fullName);
    }

    /**
     * Valida los datos básicos del paciente
     * 
     * @param patient Paciente a validar
     * @throws IllegalArgumentException Si los datos no son válidos
     */

    // esto se hiria para validator
    private void validatePatientData(Patient patient) {
        if (patient.getFirstName() == null || patient.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente es requerido");
        }

        if (patient.getLastName() == null || patient.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del paciente es requerido");
        }

        // if (patient.getDocumentId() == null ||
        // patient.getDocumentId().trim().isEmpty()) {
        // throw new IllegalArgumentException("El documento de identidad es requerido");
        // }

        if (patient.getBirthDate() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es requerida");
        }

        if (patient.getBirthDate().isAfter(java.time.LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }

        // if (patient.getPhoneNumber() == null ||
        // patient.getPhoneNumber().trim().isEmpty()) {
        // throw new IllegalArgumentException("El número de teléfono es requerido");
        // }
    }

    /**
     * Obtiene la edad del paciente en años
     * 
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
     * 
     * @param patient Paciente a verificar
     * @return true si es mayor de edad, false en caso contrario
     */
    public boolean isAdultPatient(Patient patient) {
        return calculatePatientAge(patient) >= 18;
    }

}