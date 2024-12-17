package com.global.hr.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.global.hr.Exceptions.EntityNotFoundException;
import com.global.hr.Exceptions.InvalidEntityException;

import com.global.hr.entity.Roles;
import com.global.hr.repository.RolesRepo;
import com.global.hr.service.RolesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService{
    private final RolesRepo rolesRepo;
    
    @Override
    public Roles findById(Long id) {
        return rolesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + id));
    }

    @Override
    public List<Roles> findAll() {
        return rolesRepo.findAll();
    }

    @Override
    public Optional<Roles> findByRoleName(String roleName) {
        return rolesRepo.findByRoleName(roleName);

    }

    @Override
    public Roles insert(Roles entity) {
        if (entity.getRoleName() == null || entity.getRoleName().isEmpty()) {
            throw new InvalidEntityException("Role name cannot be empty.");
        }
        return rolesRepo.save(entity);
    }

    @Override
    public Roles update(Roles entity) {
        Roles currentRoles = rolesRepo.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found with ID: " + entity.getId()));
    
        currentRoles.setRoleName(entity.getRoleName());
        return rolesRepo.save(currentRoles);
    }
    

    @Override
    public void deleteById(Long id) {
        rolesRepo.deleteById(id);
    }

}
