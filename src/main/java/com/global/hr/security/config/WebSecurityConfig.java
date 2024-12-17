package com.global.hr.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Désactiver CSRF pour simplifier les tests via Postman
            .csrf(csrf -> csrf.disable())
            
            // Configuration des autorisations
            .authorizeHttpRequests(auth -> auth
                // Accès pour les projets
                .requestMatchers(HttpMethod.GET, "/projets/**").hasAnyRole("Admin", "Manager")
                .requestMatchers(HttpMethod.POST, "/projets").hasAnyRole("Admin", "Manager")
                .requestMatchers(HttpMethod.PUT, "/projets/**").hasAnyRole("Admin", "Manager")
                .requestMatchers(HttpMethod.DELETE, "/projets/**").hasAnyRole("Admin", "Manager")

                // Accès pour les expériences
                .requestMatchers(HttpMethod.GET, "/experience/**").hasAnyRole("Admin", "Employer")
                .requestMatchers(HttpMethod.POST, "/experience").hasAnyRole("Admin", "Employer")
                .requestMatchers(HttpMethod.PUT, "/experience/**").hasAnyRole("Admin", "Employer")
                .requestMatchers(HttpMethod.DELETE, "/experience/**").hasAnyRole("Admin", "Employer")

                // Accès pour les affectations
                .requestMatchers(HttpMethod.GET, "/affictation/**").hasAnyRole("Admin", "Manager")
                .requestMatchers(HttpMethod.POST, "/affictation").hasAnyRole("Admin", "Manager")
                .requestMatchers(HttpMethod.PUT, "/affictation/**").hasAnyRole("Admin", "Manager")
                .requestMatchers(HttpMethod.DELETE, "/affictation/**").hasAnyRole("Admin", "Manager")

                // Accès pour les rôles (uniquement Admin)
                .requestMatchers("/role/**").hasRole("Admin")

                // Accès pour les utilisateurs (uniquement Admin)
                .requestMatchers("/user/**").hasRole("Admin")

                // Toutes les autres requêtes nécessitent une authentification
                .anyRequest().authenticated()
            )

            // Gestion des sessions (Stateless pour JWT par exemple)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Configuration HTTP Basic pour l'authentification
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
