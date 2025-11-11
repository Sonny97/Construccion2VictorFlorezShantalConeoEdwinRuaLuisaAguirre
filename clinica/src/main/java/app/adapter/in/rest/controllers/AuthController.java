package app.adapter.in.rest.controllers;

import app.domain.model.auth.AuthCredentials;
import app.adapter.rest.mapper.AuthRestMapper;
import app.adapter.rest.request.AuthRequest;
import app.adapter.rest.response.TokenResponseDto;
import app.application.usecase.LoginUseCase;
import app.domain.model.auth.TokenResponse;
import app.domain.ports.AuthenticationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthRestMapper authRestMapper;
    
    @Autowired
    private LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody AuthRequest request) throws Exception {

        AuthCredentials credentials = authRestMapper.toDomain(request);
        System.out.println(" Login attempt for: " + credentials.getUsername());

        TokenResponse token = loginUseCase.login(credentials);
        System.out.println(" Token generated  ");
        return ResponseEntity.ok(authRestMapper.toResponse(token));
    }

}