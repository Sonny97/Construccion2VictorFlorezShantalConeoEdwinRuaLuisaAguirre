package app.adapter.in.client;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.UserBuilder;
import app.application.useCase.UserUseCase;
import app.domain.model.User;

@Controller
public class HRClient {

    private static Scanner scanner = new Scanner(System.in);

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private UserBuilder userBuilder;

    public void session() {
        boolean inSession = true;

        while (inSession) {
            System.out.println("\n=== Menú de Recursos Humanos ===");
            System.out.println("1. Crear usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> createUserMenu();
                case "2" -> listUsers();
                case "3" -> deleteUser();
                case "4" -> {
                    System.out.println("Cerrando sesión de RH...");
                    inSession = false;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void createUserMenu() {
        System.out.println("\n--- Tipo de usuario a crear ---");
        System.out.println("1. Recursos Humanos");
        System.out.println("2. Administrativo");
        System.out.println("3. Enfermero/a");
        System.out.println("4. Médico");
        System.out.print("Seleccione: ");

        String option = scanner.nextLine();

        try {
            User user = readUserInfo();

            switch (option) {
                case "1" -> userUseCase.createHRUser(user);
                case "2" -> userUseCase.createAdministrativeUser(user);
                case "3" -> userUseCase.createNurseUser(user);
                case "4" -> userUseCase.createDoctorUser(user);
                default -> System.out.println("Opción inválida");
            }

            System.out.println("✅ Usuario creado exitosamente!");

        } catch (Exception e) {
            System.out.println("❌ Error al crear usuario: " + e.getMessage());
        }
    }

    private User readUserInfo() throws Exception {

        System.out.println("\n--- Información del usuario ---");
        System.out.print("Ingrese nombre: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese apellido: ");
        String lastName = scanner.nextLine();
        System.out.print("Ingrese correo: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese cédula (número): ");
        int documentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese fecha de nacimiento (YYYY-MM-DD): ");
        String birthDate = scanner.nextLine();

        System.out.print("Ingrese género: ");
        String gender = scanner.nextLine();
        System.out.print("Ingrese dirección: ");
        String address = scanner.nextLine();

        System.out.print("Ingrese teléfono (número): ");
        int phoneNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el nombre de usuario: ");
        String userName = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        return userBuilder.build(
                firstName,
                lastName,
                email,
                documentId,
                birthDate,
                gender,
                address,
                phoneNumber,
                userName,
                password);
    }

    private void listUsers() {
        try {
            List<User> users = userUseCase.listAllUsers();

            if (users.isEmpty()) {
                System.out.println(" No hay usuarios registrados");
                return;
            }

            System.out.println("\n===  LISTA DE USUARIOS REGISTRADOS ===");

            for (User u : users) {
                System.out.println("┌──────────────────────────────────────────────────┐");
                System.out.println("│ ID: " + u.getId());
                System.out.println("│ Nombre completo: " + u.getFirstName() + " " + u.getLastName());
                System.out.println("│ Usuario: " + u.getUserName());
                System.out.println("│ Correo: " + u.getEmail());
                System.out.println("│ Rol: " + u.getRole());
                System.out.println("│ Cédula: " + u.getDocumentId());
                System.out.println("│ Fecha de nacimiento: " + u.getBirthDate());
                System.out.println("│ Género: " + u.getGender());
                System.out.println("│ Dirección: " + u.getAddress());
                System.out.println("│ Teléfono: " + u.getPhoneNumber());
                System.out.println("└──────────────────────────────────────────────────┘");
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println(" Error al listar usuarios: " + e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.print("Ingrese el nombre de usuario a eliminar: ");
        String username = scanner.nextLine();

        try {
            userUseCase.deleteUser(username);
            System.out.println(" Usuario eliminado exitosamente!");
        } catch (Exception e) {
            System.out.println(" Error al eliminar usuario: " + e.getMessage());
        }
    }
}