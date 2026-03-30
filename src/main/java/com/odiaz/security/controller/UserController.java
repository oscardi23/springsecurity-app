package com.odiaz.security.controller;


import com.odiaz.security.dtos.UserDto;
import com.odiaz.security.entities.UserEntity;
import com.odiaz.security.services.UserService;
import com.odiaz.security.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;



    // metodo crear usuario
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) throws Exception {

        if (userDto == null) {
             throw new Exception("User is null");
        }

        UserEntity  userEntity = UserMapper.fromDtoToEntity(userDto, new UserEntity());

       userService.createUser(userEntity);

       return new ResponseEntity<>(HttpStatus.CREATED);

    }


    // metodo para buscar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    // metodo para actualizar un usuario

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser (@PathVariable Integer id ,  @RequestBody UserDto userDto) {

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserEntity updateUser = userService.updateUser(id, userDto);
        UserDto updateUserDto = UserMapper.fromEntityTODto(updateUser, new UserDto());

        return ResponseEntity.ok(updateUserDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Integer id) {

        userService.deleteUser(id);
        Map<String, String> response = new HashMap<>();

        response.put("message", "User deleted successfully");
        return  ResponseEntity.ok(response);
    }

}

