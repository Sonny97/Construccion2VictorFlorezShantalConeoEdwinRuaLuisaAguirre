package app.infrastructure.security;

import app.domain.ports.AuthenticationPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationPort authenticationPort;

    public JwtAuthenticationFilter(AuthenticationPort authenticationPort) {
        this.authenticationPort = authenticationPort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String token = this.extractToken(request);

        if (token != null) {
            this.processToken(token);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private void processToken(String token) {
        if (authenticationPort.validateToken(token)) {
            String username = authenticationPort.extractUsername(token);
            String role = authenticationPort.extractRole(token);
            System.out.println("👤 User from token: " + username);
            System.out.println("🎭 Role from token: " + role);

            if (username != null && role != null && !role.trim().isEmpty()) {
                String normalizedRole = role.trim().toUpperCase();
                if (!normalizedRole.startsWith("ROLE_")) {
                    normalizedRole = "ROLE_" + normalizedRole;
                }
                System.out.println("✅ Normalized role: " + normalizedRole);

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(normalizedRole));

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("🔓 Authentication set successfully");
            }
        } else {
            System.out.println("❌ Token validation failed");
        }
    }
}