package app.adapter.in.rest.controllers;

import app.domain.model.auth.AuthCredentials;
import app.domain.model.auth.TokenResponse;
import app.domain.ports.AuthenticationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationPort authenticationPort;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthCredentials credentials) {
        // Por ahora usamos rol fijo "HUMAN_RESOURCES" para probar
        // Luego puedes obtener el rol de la base de datos según el usuario
        TokenResponse response = authenticationPort.authenticate(credentials, "HUMAN_RESOURCES");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/{role}")
    public ResponseEntity<TokenResponse> loginWithRole(
            @RequestBody AuthCredentials credentials, 
            @PathVariable String role) {
        TokenResponse response = authenticationPort.authenticate(credentials, role);
        return ResponseEntity.ok(response);
    }
}