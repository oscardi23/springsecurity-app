package com.odiaz.security.services.mappers;


import com.odiaz.security.dtos.UserDto;

import com.odiaz.security.entities.Role;
import com.odiaz.security.entities.UserEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class UserMapper {

    public static UserEntity fromDtoToEntity(UserDto user, UserEntity entity) {

        if(user == null || entity == null){
            return null;
        }

       entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setActive(user.getActive());

        Role role = new Role();
        role.setName(user.getRole());
        entity.setRole(role);


        return entity;
    }

    public static UserDto fromEntityTODto(UserEntity entity, UserDto userDto ) {

        if(entity == null || userDto == null){
            return null;
        }
        userDto.setId(entity.getId());
        userDto.setUsername(entity.getUsername());
        userDto.setPassword(entity.getPassword());
        userDto.setActive(entity.getActive());


        if (entity.getRole() != null){

            userDto.setRole(entity.getRole().getName());

        }
        return userDto;

    }
}
