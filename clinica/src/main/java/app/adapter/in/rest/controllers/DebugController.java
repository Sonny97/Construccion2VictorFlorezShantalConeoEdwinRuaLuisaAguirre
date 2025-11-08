package app.adapter.in.rest.controllers;

import app.domain.ports.AuthenticationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/debug")
public class DebugController {
    
    @Autowired
    private AuthenticationPort authenticationPort;

    @GetMapping("/token-info")
    public ResponseEntity<String> getTokenInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = authenticationPort.extractUsername(token);
            String role = authenticationPort.extractRole(token);
            
            String info = "Username: " + username + ", Role: " + role;
            System.out.println("🔍 DEBUG: " + info);
            
            return ResponseEntity.ok(info);
        }
        return ResponseEntity.badRequest().body("No token provided");
    }
}