package com.global.hr.dto;


import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class RolesDto implements GrantedAuthority{

    private Long id;
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }

}
