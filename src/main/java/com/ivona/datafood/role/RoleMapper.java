package com.ivona.datafood.role;

import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public RoleDto toDto(Role entity){

        if(entity == null){
            return null;
        }

        RoleDto dto = new RoleDto();

        dto.setName(entity.getName());


                return dto;
    }

    public Role fromDto(RoleDto dto){
        if  (dto == null){
            return null;
        }

        Role entity = new Role();

        entity.setName(dto.getName());


        return entity;
    }
}
