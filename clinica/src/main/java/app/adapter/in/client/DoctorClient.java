// package app.adapter.in.client;

// import java.time.LocalDate;
// import java.time.format.DateTimeParseException;
// import java.util.Scanner;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;

// import app.adapter.in.builder.PatientBuilder;
// import app.application.useCase.MedicUsecase;
// import app.application.useCase.PatientUseCase;
// import app.domain.model.Patient;

// @Controller
// public class DoctorClient {

//     private static Scanner scanner = new Scanner(System.in);

//     @Autowired
//     private MedicUsecase medicUseCase;

//     @Autowired
//     private PatientUseCase patientUseCase;

//     @Autowired
//     private PatientBuilder patientBuilder;

//     public void session() {
//         boolean inSession = true;

//         while (inSession) {
//             System.out.println("\n=== Menú de Doctor ===");
//             System.out.println("1. Crear Registro medico del paciente");
//             System.out.println("2. Obtener registro medico del paciente");
//             System.out.println("3. Actualizar registro medico");
//             System.out.print("Seleccione una opción: ");

//             String option = scanner.nextLine();

//             switch (option) {
//                 case "1" -> createMedicalRegister();
//                 case "2" -> getMedicalRegister();
//                 case "3" -> updateMedicalRegister();
//                 case "4" -> {
//                     System.out.println("Cerrando sesión del Medico...");
//                     inSession = false;
//                 }
//                 default -> System.out.println("Opción NO válida (invalida sera su abuela)");
//             }
//         }
//     }

//     private void createMedicalRegister() {

//         try {
//             Patient patient = readPatientInfo();

//             patientBuilder.build(
//                 patient.getFirstName,
//                 patient.getLastName,
//                 patient.getEmail,
//                 patient.getDocumentId,
//                 patient.getBirthDate,
//                 patient.getGender,
//                 patient.getAddress,
//                 patient.getPhoneNumber,
//                 );

//             System.out.println("✅ Paciente creado exitosamente!");

//         } catch (Exception e) {
//             System.out.println("❌ Error al crear Paciente: " + e.getMessage());
//         }
//     }

//     private Patient readPatientInfo() throws Exception {

//         System.out.println("\n--- Información del Paciente ---");
//         System.out.print("Ingrese nombre: ");
//         String firstName = scanner.nextLine();
//         System.out.print("Ingrese apellido: ");
//         String lastName = scanner.nextLine();
//         System.out.print("Ingrese correo: ");
//         String email = scanner.nextLine();

//         System.out.print("Ingrese cédula (número): ");
//         int documentId = Integer.parseInt(scanner.nextLine());

//         System.out.print("Ingrese fecha de nacimiento (YYYY-MM-DD): ");
//         String birthDate = scanner.nextLine();

//         System.out.print("Ingrese género M/F: ");
//         String gender = scanner.nextLine();
//         System.out.print("Ingrese dirección: ");
//         String address = scanner.nextLine();

//         System.out.print("Ingrese teléfono (número): ");
//         int phoneNumber = Integer.parseInt(scanner.nextLine());
//         Patient patient = new Patient(firstName, lastName, email, documentId, birthDate, gender, address, phoneNumber);
//         return patient
//     }

//     private void getMedicalRegister() {
//         System.out.print("Ingrese cédula (número): ");
//         int documentId = Integer.parseInt(scanner.nextLine());
//         try {
//             Patient patient = patientUseCase.findByIdNumber(documentId);

//             if (patient.isEmpty()) {
//                 System.out.println(" No hay paciente registrado con el id: " + documentId);
//                 return;
//             }
            
//             System.out.println("┌──────────────────────────────────────────────────┐");
//             System.out.println("│ ID: " + patient.getId());
//             System.out.println("│ Nombre completo: " + patient.getFirstName() + " " + patient.getLastName());
//             System.out.println("│ Correo: " + patient.getEmail());
//             System.out.println("│ Rol: " + patient.getRole());
//             System.out.println("│ Cédula: " + patient.getDocumentId());
//             System.out.println("│ Fecha de nacimiento: " + patient.getBirthDate());
//             System.out.println("│ Género: " + patient.getGender());
//             System.out.println("│ Dirección: " + patient.getAddress());
//             System.out.println("│ Teléfono: " + patient.getPhoneNumber());
//             System.out.println("└──────────────────────────────────────────────────┘");
//             System.out.println();

//         } catch (Exception e) {
//             System.out.println(" Error al gestionarl el paciente: " + e.getMessage());
//         }
//     }

//     private void updateMedicalRegister() {
//         System.out.print("Ingrese cédula (número): ");
//         int documentId = Integer.parseInt(scanner.nextLine());
//         Patient patient = readPatientInfo();
//         try {
//             patientUseCase.updatePatient(patient);
//         } catch (Exception e) {
//             System.out.println(" Error al actualizar el paciente: " + e.getMessage());
//         }
//     }
// }