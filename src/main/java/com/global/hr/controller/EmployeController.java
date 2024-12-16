package com.global.hr.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.dto.EmployeDto;
import com.global.hr.entity.Employe;
import com.global.hr.mapper.EmployeMapper;
import com.global.hr.service.EmployeService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RequiredArgsConstructor
@RestController
@RequestMapping("/employe")
public class EmployeController {

    private final EmployeService employeService;

    private final EmployeMapper employeMapper;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeDto> findById(@PathVariable Long id) {

        Employe entity = employeService.findById(id);
        EmployeDto empDto = employeMapper.map(entity);

        return ResponseEntity.ok(empDto);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeDto>> findAll() {
        List<Employe> entites = employeService.findAll();
        List<EmployeDto> empDtos = employeMapper.map(entites);

        return ResponseEntity.ok(empDtos);
    }

    @GetMapping("/filtre")
    public ResponseEntity<EmployeDto> filtre(@RequestParam String FirstName, @RequestParam String LastName) {

        Employe entity = employeService.findByFirstNameAndLastNameOrderById(FirstName, LastName);
        EmployeDto empDto = employeMapper.map(entity);

        return ResponseEntity.ok(empDto);
    }

    @PostMapping()
    public ResponseEntity<EmployeDto> insert(@RequestBody EmployeDto dto) {

        Employe emp = employeMapper.unMap(dto);
        Employe entity = employeService.insert(emp);
        EmployeDto rdto = employeMapper.map(entity);


        return ResponseEntity.ok(rdto);
    }

    @PutMapping()
    public ResponseEntity<EmployeDto> update(@RequestBody EmployeDto dto) {
    
        // Récupération de l'entité actuelle
        Employe currentEmp = employeService.findById(dto.getId());
        
        // Mise à jour de l'entité avec les données du DTO
        employeMapper.updateEntityFromDto(currentEmp, dto);
        
        // Enregistrement de l'entité mise à jour
        Employe updatedEmp = employeService.update(currentEmp);
        
        // Conversion en DTO pour la réponse
        EmployeDto rdto = employeMapper.map(updatedEmp);
    
        return ResponseEntity.ok(rdto);
    }
    

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }


}
