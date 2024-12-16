package com.global.hr.service;

import java.util.List;

import com.global.hr.entity.Employe;


public interface EmployeService {
    Employe findById(Long id);

    List<Employe> findAll();

    Employe findByFirstNameAndLastNameOrderById(String FirstName , String LastName);

    Employe insert(Employe Entity);

    Employe update(Employe Entity);

    void deleteById(Long id);


}
