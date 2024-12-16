package com.global.hr.service;

import java.util.List;

import com.global.hr.entity.AppUser;

public interface AppUserService {

    AppUser findById(Long id);

    List<AppUser> findAll();

    AppUser findByUserName(String userName);

    AppUser insert(AppUser Entity);

    AppUser update(AppUser Entity);

    void deleteById(Long id);

}
