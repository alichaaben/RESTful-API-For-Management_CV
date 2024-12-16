package com.global.hr.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.hr.entity.AppUser;
import com.global.hr.repository.AppUserRepo;
import com.global.hr.service.AppUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{
    
    private final AppUserRepo appUserRepo;

    @Override
    public AppUser findById(Long id) {
        return appUserRepo.findById(id).orElse(null);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepo.findAll();
    }
    
    @Override
    public AppUser findByUserName(String userName) {
        return appUserRepo.findByUserName(userName);
    }

    @Override
    public AppUser insert(AppUser Entity) {
        return appUserRepo.save(Entity);
    }

    @Override
    public AppUser update(AppUser Entity) {
        AppUser currentUser = appUserRepo.findById(Entity.getId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        currentUser.setUserName(Entity.getUserName());
        currentUser.setEmail(Entity.getEmail());
        currentUser.setTelephone(Entity.getTelephone());
        currentUser.setMotDePasse(Entity.getMotDePasse());
        
        return appUserRepo.save(currentUser);
    }

    @Override
    public void deleteById(Long id) {
        appUserRepo.deleteById(id);
    }


}
