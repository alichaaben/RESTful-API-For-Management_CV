package com.global.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employe;



@Repository
public interface EmployeRepo extends JpaRepository <Employe,Long>{

    //@Query(value = "select * from Employe where id = 1",nativeQuery = true)
    Employe findByFirstNameAndLastNameOrderById(String FirstName , String LastName);

}
