 package app.infrastructure.security;
 import app.domain.ports.AuthenticationPort;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.security.web.SecurityFilterChain;
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 @Configuration
 @EnableWebSecurity
 @EnableMethodSecurity(prePostEnabled = true)
 public class SecurityConfig {
     private final AuthenticationPort authenticationPort;
     public SecurityConfig(AuthenticationPort authenticationPort) {
         this.authenticationPort = authenticationPort;
     }
     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
             .csrf().disable()
             .authorizeHttpRequests()
                 .requestMatchers("/api/auth/login/**").permitAll()
                 .requestMatchers("/api/rh/**").hasRole("HUMAN_RESOURCES")
                 .requestMatchers("/api/medic/**").hasRole("MEDIC")
                 .requestMatchers("/api/nurse/**").hasRole("NURSE")
                 .requestMatchers("/api/admin/**").hasRole("ADMINISTRATIVE")
                 .anyRequest().authenticated()
             .and()
             .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
      
         return http.build();
     }
     @Bean
     public JwtAuthenticationFilter jwtAuthenticationFilter() {
         // ✅ CORREGIDO: Pasar authenticationPort como argumento
         return new JwtAuthenticationFilter(authenticationPort);
     }
 //     @bean
     public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }
 }