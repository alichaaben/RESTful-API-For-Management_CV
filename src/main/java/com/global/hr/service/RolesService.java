package com.global.hr.service;

import java.util.List;
import java.util.Optional;

import com.global.hr.entity.Roles;

public interface RolesService {

    Roles findById(Long id);

    List<Roles> findAll();

    Optional<Roles> findByRoleName(String roleName);

    Roles insert(Roles Entity);

    Roles update(Roles Entity);

    void deleteById(Long id);

}
