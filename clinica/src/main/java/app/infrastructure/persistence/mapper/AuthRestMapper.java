package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import app.application.DTOs.AuthRequest;
import app.application.DTOs.TokenResponseDto;
import app.domain.model.auth.AuthCredentials;
import app.domain.model.auth.TokenResponse;

@Component
public class AuthRestMapper {
    public AuthCredentials toDomain(AuthRequest req) {
        AuthCredentials c = new AuthCredentials();
        c.setUsername(req.getUsername());
        c.setPassword(req.getPassword());
        return c;
    }

    public TokenResponseDto toResponse(TokenResponse token) {
        return new TokenResponseDto(token.getToken());
    }
}
