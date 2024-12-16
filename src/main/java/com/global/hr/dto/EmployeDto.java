package com.global.hr.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeDto {

    private Long id;
    private String userName;
    private String empfirstName;
    private String emplastName;
    private LocalDate empHirDate;
}
