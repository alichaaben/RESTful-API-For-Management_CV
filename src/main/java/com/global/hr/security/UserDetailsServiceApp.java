package com.global.hr.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.global.hr.entity.AppUser;
import com.global.hr.repository.AppUserRepo;

import java.util.Collections;

@Service
public class UserDetailsServiceApp implements UserDetailsService {

    private final AppUserRepo appUserRepo;

    public UserDetailsServiceApp(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Récupérer l'utilisateur depuis la base de données
        AppUser user = appUserRepo.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found !!");
        }

        // Convertir le rôle en GrantedAuthority
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());

        // Retourner l'objet User avec les autorités
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),                 // Nom d'utilisateur
                user.getMotDePasse(),               // Mot de passe
                Collections.singletonList(authority) // Liste des autorités
        );
    }
}
