package com.global.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.AppUser;

@Repository
public interface AppUserRepo extends JpaRepository <AppUser,Long> {

    AppUser findByUserName(String userName);

}
