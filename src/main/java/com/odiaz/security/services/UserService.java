package com.odiaz.security.services;

import com.odiaz.security.dtos.UserDto;
import com.odiaz.security.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService  {

   Optional<UserEntity> findByUsernameActive(String username, Boolean active);

   UserEntity createUser(UserEntity userEntity);

   List<UserDto> findAll();

   UserEntity updateUser(Integer id, UserDto userDto);

   void deleteUser(Integer id);





}
