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
        
        System.out.println("🔐 Login attempt for: " + credentials.getUsername());
        
        TokenResponse response = authenticationPort.authenticate(credentials, "HUMAN_RESOURCES");
        System.out.println("✅ Token generated for role: HUMAN_RESOURCES");
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