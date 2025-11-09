package app.domain.model.auth;

import app.domain.model.emuns.Role;


public class TokenResponse {
    private String token;
    private String role;
    

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}