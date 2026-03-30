package com.odiaz.security.services.mappers;

import com.odiaz.security.dtos.RoleDto;
import com.odiaz.security.entities.Role;
import com.odiaz.security.enums.RoleList;

public class RoleMapper {

    public static Role fromDtoToEntity(RoleDto role, Role entity) {
        entity.setId(role.getId());
        entity.setName(RoleList.valueOf(role.getName()));
        return entity;
    }

    public static RoleDto fromEntityTODto(Role entity, RoleDto roleDto ) {
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName().name());
        return roleDto;

    }
}
