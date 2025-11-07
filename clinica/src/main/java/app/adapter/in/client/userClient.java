// package app.adapter.in.client;

// import java.util.Scanner;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;

// import app.adapter.in.builder.UserBuilder;
// import app.application.useCase.HumanResourcesUseCase;
// import app.domain.model.User;
// import app.application.useCase.AuthUseCase;
// import app.domain.model.emuns.Role;

// @Controller
// public class userClient {

//     private static Scanner scanner = new Scanner(System.in);

//      @Autowired
//     private AuthUseCase authUseCase;
    
//     @Autowired
//     private HRClient hrClient;
    
//     @Autowired
//     private AdminClient adminClient;
    
//     @Autowired
//     private NurseClient nurseClient;
    
//     @Autowired
//     private DoctorClient doctorClient;

//     private User currentUser;
//     private boolean systemRunning = true;

//     public void start() {
//         System.out.println("🏥 BIENVENIDO AL SISTEMA DE CLÍNICA");

//         while (systemRunning) {
//             if (currentUser == null) {
//                 authenticateUser();
//             } else {
//                 showRoleMenu();
//             }
//         }
//     }

//     private void authenticateUser() {
//         System.out.println("\n=== INICIO DE SESIÓN ===");
//         System.out.print("Usuario: ");
//         String username = scanner.nextLine();
//         System.out.print("Contraseña: ");
//         String password = scanner.nextLine();

//         try {
//             currentUser = authUseCase.authenticate(username, password);
//             if (currentUser != null) {
//                 System.out.println("✅ Inicio de sesión exitoso!");
//                 System.out.println("Bienvenido: " + currentUser.getUserName() +
//                         " (" + currentUser.getRole() + ")");
//             } else {
//                 System.out.println(" Usuario o contraseña incorrectos");
//             }
//         } catch (Exception e) {
//             System.out.println(" Error en autenticación: " + e.getMessage());
//         }
//     }

//     private void showRoleMenu() {
//         switch (currentUser.getRole()) {
//             case HUMAN_RESOURCES -> hrClient.session();
//             //case ADMINISTRATIVE -> adminClient.session();
//             //case NURSE -> nurseClient.session();
//             //case MEDIC -> doctorClient.session();
//             default -> {
//                 System.out.println("Rol no reconocido.");
//                 systemRunning = false;
//             }
//         }

//         currentUser = null;
//     }

// }
