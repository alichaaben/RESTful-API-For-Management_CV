package com.global.hr.security.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Value("${jwt.secret}")
    private String SecretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Désactiver CSRF pour simplifier les tests via Postman
            .csrf(csrf -> csrf.disable())
            
            // Configuration des autorisations
            .authorizeHttpRequests(auth -> auth
                // Accès libre pour le login
                .requestMatchers("/auth/login/**").permitAll()

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

            // Gestion des sessions (Stateless pour JWT)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Configuration OAuth2 Resource Server pour JWT
            .oauth2ResourceServer(oa -> oa.jwt(Customizer.withDefaults()));

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Cette méthode retourne un encodeur de mot de passe BCrypt qui est utilisé pour hacher et vérifier les mots de passe de manière sécurisée
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    JwtEncoder jwtEncoder() {
        // Cette méthode retourne un encodeur JWT Nimbus qui utilise une clé secrète pour signer les jetons JWT
        // ImmutableSecret crée une clé secrète immuable à partir de la clé spécifiée dans les propriétés de l'application
        return new NimbusJwtEncoder(new ImmutableSecret<>(SecretKey.getBytes()));
    }
    
    @Bean
    JwtDecoder jwtDecoder() {
        // Cette méthode retourne un décodeur JWT Nimbus qui permet de valider et de décoder un jeton JWT en utilisant une clé secrète
        // La clé secrète est utilisée pour vérifier la signature du jeton et s'assurer qu'il est authentique
        SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKey.getBytes(), "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        // Cette méthode crée un AuthenticationManager qui est utilisé pour gérer l'authentification des utilisateurs
        // DaoAuthenticationProvider est utilisé pour l'authentification via un UserDetailsService et un encodeur de mot de passe
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // Utilisation de l'encodeur BCrypt pour vérifier les mots de passe
        daoAuthenticationProvider.setUserDetailsService(userDetailsService); // Définition du service qui charge les utilisateurs depuis la base de données
        return new ProviderManager(daoAuthenticationProvider); // Le ProviderManager gère l'authentification via le DaoAuthenticationProvider
    }
    
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Cette méthode crée un convertisseur qui permet d'extraire et de convertir les rôles d'un utilisateur à partir d'un jeton JWT
        // Le JwtGrantedAuthoritiesConverter convertit les rôles (présents dans le champ "scope" du JWT) en autorités utilisables par Spring Security
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix(""); // Le préfixe ROLE_ est supprimé pour éviter les doublons (les rôles sont déjà dans ce format)
        grantedAuthoritiesConverter.setAuthoritiesClaimName("scope"); // Les rôles sont extraits du champ "scope" dans le JWT
    
        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter); // Le JwtAuthenticationConverter utilise le JwtGrantedAuthoritiesConverter pour gérer les rôles
        return authenticationConverter; // Ce convertisseur est utilisé pour appliquer les rôles à l'utilisateur authentifié
    }
    }
