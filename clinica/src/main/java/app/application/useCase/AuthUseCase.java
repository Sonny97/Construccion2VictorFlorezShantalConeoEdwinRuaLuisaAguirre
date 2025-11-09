package app.application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.services.AuthService;
import app.infrastructure.security.JwtAdapter;

@Service
public class AuthUseCase {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtAdapter jwtService; // ✅ Inyectar JwtService

    public String authenticate(String username, String password) throws Exception {
        System.out.println("🔐 Authenticating user: " + username);
        
        // 1. Autenticar usuario (verificar credenciales)
        Employee user = authService.authenticate(username, password);
        
        // 2. Generar token JWT usando el servicio
        String token = jwtService.generateToken(user.getUserName(), user.getRole().name());
        
        System.out.println("✅ Token generated for user: " + user.getUserName() + ", Role: " + user.getRole());
        return token;
    }

    // Método para obtener usuario
    public Employee getUserByUsername(String username) throws Exception {
        return authService.getUserByUsername(username);
    }
}