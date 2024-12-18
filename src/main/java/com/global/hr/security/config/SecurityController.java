package com.global.hr.security.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder jwtEncoder;

    @GetMapping("/Profile")
    //@PreAuthorize("hasAuthority('Role_Admin')")
    public Authentication authentication(Authentication authentication){
        // Cette méthode reçoit un objet Authentication, qui contient les informations d'authentification de l'utilisateur actuellement connecté.
        // L'objet Authentication contient des détails comme le nom d'utilisateur (principal), les rôles (autorités) et d'autres informations pertinentes.
        // Ici, cette méthode retourne simplement l'objet Authentication tel quel sans modification.
        // Cela pourrait être utilisé pour vérifier ou retourner l'état d'authentification de l'utilisateur dans l'application.
    
        return authentication;
    }
    
    @PostMapping("/login")
public Map<String, String> login(@RequestParam String userName, @RequestParam String password) {
    
    // Cette ligne crée un objet Authentication en utilisant le nom d'utilisateur et le mot de passe fournis
    // L'AuthenticationManager valide l'authentification de l'utilisateur
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userName, password)
    );
    
    // La variable instant capture l'heure actuelle (au moment de l'authentification)
    Instant instant = Instant.now();
    
    // La variable "scope" collecte les rôles (autorités) de l'utilisateur sous forme de chaîne séparée par des espaces
    // Exemple : "ROLE_USER ROLE_ADMIN"
    String scope = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));
    
    // Construction de l'objet JwtClaimsSet qui contient les informations du jeton JWT
    // - "issuedAt" : la date et l'heure d'émission
    // - "expiresAt" : la date et l'heure d'expiration du jeton (10 minutes après l'émission)
    // - "subject" : l'identifiant de l'utilisateur (ici, le nom d'utilisateur)
    // - "scope" : les rôles de l'utilisateur
    JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
        .issuedAt(instant)  // Date d'émission du jeton
        .expiresAt(instant.plus(10, ChronoUnit.MINUTES))  // Date d'expiration du jeton (10 minutes après)
        .subject(userName)  // L'utilisateur authentifié
        .claim("scope", scope)  // Les rôles de l'utilisateur (dans le champ "scope")
        .build();
    
    // Création de l'objet JwtEncoderParameters avec l'en-tête (header) et les informations de payload (body)
    JwtEncoderParameters jwtEncoderParameters =
        JwtEncoderParameters.from(
            JwsHeader.with(MacAlgorithm.HS512).build(),  // L'algorithme de signature (HS512)
            jwtClaimsSet  // Les informations de payload
        );

    // L'encodeur génère le jeton JWT en utilisant les paramètres spécifiés
    String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    
    // Retourne le jeton JWT sous forme de map avec la clé "access-token"
    return Map.of("access-token", jwt);
}
}
