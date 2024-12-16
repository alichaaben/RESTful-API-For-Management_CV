package com.global.hr.service.Impl;


import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.entity.Employe;
import com.global.hr.repository.EmployeRepo;
import com.global.hr.service.EmployeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    // @Autowired
    // private EmployeRepo employeRepo;

    private final  EmployeRepo employeRepo;

    @Override
    public Employe findById(Long id) {
        return employeRepo.findById(id).orElse(null);
    }


    @Override
    public List<Employe> findAll() {
        return employeRepo.findAll();
    }

    @Override
    public Employe findByFirstNameAndLastNameOrderById(String FirstName, String LastName) {
         return employeRepo.findByFirstNameAndLastNameOrderById(FirstName, LastName);
    }

    @Override
    public Employe insert(Employe Entity) {
        return employeRepo.save(Entity);
    }

    @Override
    public Employe update(Employe Entity) {
        
        Employe currentEmp = employeRepo.findById(Entity.getId())
        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        
        
        currentEmp.setFirstName(Entity.getFirstName());
        currentEmp.setLastName(Entity.getLastName());
        currentEmp.setHirDate(Entity.getHirDate());


        return employeRepo.save(currentEmp);
    }

    @Override
    public void deleteById(Long id) {
        employeRepo.deleteById(id);
    }

    

}
