package com.global.hr.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.global.hr.dto.RolesDto;
import com.global.hr.entity.Roles;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RolesMapper {

    @Mapping(source = "roleName", target = "roleName")
    RolesDto map(Roles entity);


    
    List<RolesDto> map(List<Roles> entities);

    
    @Mapping(source = "roleName", target = "roleName")
    @Mapping(target = "users", ignore = true)
    Roles unMap(RolesDto dto);

    @Mapping(source = "roleName", target = "roleName")
    @Mapping(target = "users", ignore = true)
    void updateEntityFromDto(@MappingTarget Roles entity, RolesDto dto);

}
