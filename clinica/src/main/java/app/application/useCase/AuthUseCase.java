package app.application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.User;
import app.domain.services.AuthService;

@Service
public class AuthUseCase {

    @Autowired
    private AuthService authService;

    public User authenticate(String username, String password) throws Exception {
        return authService.authenticate(username, password);
    }
}