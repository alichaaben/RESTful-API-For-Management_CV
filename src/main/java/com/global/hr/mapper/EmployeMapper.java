package com.global.hr.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.global.hr.dto.EmployeDto;
import com.global.hr.entity.Employe;

@Mapper(componentModel = "spring") // Ajout pour intégration avec Spring
public interface EmployeMapper {

    // Mapping Employe -> EmployeDto
    @Mapping(source = "firstName", target = "empfirstName")
    @Mapping(source = "lastName", target = "emplastName")
    @Mapping(source = "hirDate", target = "empHirDate")
    @Mapping(target = "userName", ignore = true) // Ignorer userName car généré manuellement
    EmployeDto map(Employe entity);

    // Mapping List<Employe> -> List<EmployeDto>
    List<EmployeDto> map(List<Employe> entities);

    // Mapping EmployeDto -> Employe
    @Mapping(source = "empfirstName", target = "firstName")
    @Mapping(source = "emplastName", target = "lastName")
    @Mapping(source = "empHirDate", target = "hirDate")
    Employe unMap(EmployeDto dto);

    // Mapping EmployeDto -> Employe pour mise à jour
    @Mapping(source = "empfirstName", target = "firstName")
    @Mapping(source = "emplastName", target = "lastName")
    @Mapping(source = "empHirDate", target = "hirDate") 
    void updateEntityFromDto(@MappingTarget Employe entity, EmployeDto dto);

    // Méthode custom pour générer le userName
    @AfterMapping
    default void handleUserName(Employe entity, @MappingTarget EmployeDto dto) {
        if (entity.getFirstName() != null && entity.getLastName() != null) {
            dto.setUserName(entity.getFirstName() + " " + entity.getLastName());
        }
    }
}
