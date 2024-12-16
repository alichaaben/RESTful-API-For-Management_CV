package com.global.hr.mapper;

import java.util.List;


import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.global.hr.dto.ExperienceDto;
import com.global.hr.entity.Experience;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ExperienceMapper {


    @Mapping(source = "titreExperience", target = "titreExperience")
    @Mapping(source = "poste", target = "poste")
    @Mapping(source = "activites", target = "activites")
    @Mapping(source = "user.userName", target = "userName")
    ExperienceDto map(Experience entity);

    
    List<ExperienceDto> map(List<Experience> entities);


    @Mapping(source = "titreExperience", target = "titreExperience")
    @Mapping(source = "poste", target = "poste")
    @Mapping(source = "activites", target = "activites")
    @Mapping(source = "userName", target = "user", ignore = true)
    Experience unMap(ExperienceDto dto);


    
    @Mapping(source = "titreExperience", target = "titreExperience")
    @Mapping(source = "poste", target = "poste")
    @Mapping(source = "activites", target = "activites")
    @Mapping(source = "userName", target = "user", ignore = true)
    void updateEntityFromDto(@MappingTarget Experience entity, ExperienceDto dto);



}
