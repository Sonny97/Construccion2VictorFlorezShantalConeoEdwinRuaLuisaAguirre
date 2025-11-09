package app.adapter.in.rest.controllers;

import app.domain.model.Employee;
import app.domain.model.auth.AuthCredentials;
import app.domain.model.auth.TokenResponse;
import app.domain.ports.AuthenticationPort;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationPort authenticationPort;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentials credentials) {
        try {
            System.out.println("🔐 Login attempt for: " + credentials.getUsername());

            // 1. Buscar el usuario en la BD para obtener su rol REAL
            Employee user = authUseCase.getUserByUsername(credentials.getUsername());
            if (user == null) {
                return ResponseEntity.badRequest().body("Usuario no encontrado");
            }

            // 2. Autenticar con el rol REAL del usuario
            TokenResponse response = authenticationPort.authenticate(credentials, user.getRole().name());

            System.out.println("✅ Token generated for role: " + user.getRole().name());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ Login failed: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error en autenticación");
        }
    }

    // Opcional: Endpoint para forzar rol (solo testing)
    @PostMapping("/login/{role}")
    public ResponseEntity<TokenResponse> loginWithRole(
            @RequestBody AuthCredentials credentials,
            @PathVariable String role) {
        System.out.println("🔐 Login with forced role: " + role);
        TokenResponse response = authenticationPort.authenticate(credentials, role);
        return ResponseEntity.ok(response);
    }
}