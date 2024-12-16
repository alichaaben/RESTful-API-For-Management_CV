package com.global.hr.service;

import java.util.List;

import com.global.hr.entity.Experience;

public interface ExperienceService {

    Experience findById(Long id);

    List<Experience> findAll();

    Experience insert(Experience Entity);

    Experience update(Experience Entity);

    void deleteById(Long id);

}
