package com.global.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Experience;




@Repository
public interface ExperienceRepo extends JpaRepository <Experience,Long> {

}