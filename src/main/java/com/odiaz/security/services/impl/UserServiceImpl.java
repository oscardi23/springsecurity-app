package com.odiaz.security.services.impl;


import com.odiaz.security.dtos.UserDto;
import com.odiaz.security.entities.Role;
import com.odiaz.security.entities.UserEntity;
import com.odiaz.security.exception.RoleNotFoundException;
import com.odiaz.security.exception.UserAlreadyExistException;
import com.odiaz.security.exception.UserNotFoundException;
import com.odiaz.security.exception.UserNullException;
import com.odiaz.security.repositories.RoleRepository;
import com.odiaz.security.repositories.UserRepository;
import com.odiaz.security.services.UserService;
import com.odiaz.security.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    // metodo para buscar un usuario por nombre y activo
    @Override
    public Optional<UserEntity> findByUsernameActive(String username, Boolean active) {
        return userRepository.findByUsernameActive(username, active);
    }


    // metodo para crear un usuario con un rol por defecto
    @Override
    public UserEntity createUser(UserEntity userEntity) {



       Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userEntity.getUsername());

       if (userEntityOptional.isPresent()) {
           throw new UserAlreadyExistException("User already exists");
       }

        Role defaultRole = roleRepository.findById(1)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));


        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setActive(true);
        userEntity.setRole(defaultRole);

        return userRepository.save(userEntity);

    }

    // metodo para buscar todos los usuarios
    @Override
    public List<UserDto> findAll() {

        return userRepository.findAllActiveUsers().stream()
                .map(user -> UserMapper.fromEntityTODto(user, new UserDto()))
                .toList();

    }

    @Override
    public UserEntity updateUser(Integer id, UserDto userDto) {

        if(userDto == null ){
            throw new UserNullException("User is null");
        }


       UserEntity userEntityExisting = userRepository.findById(id)
              .orElseThrow(() -> new UserNotFoundException("User not exists"));


        userEntityExisting.setUsername(userDto.getUsername()  != null ? userDto.getUsername() : userEntityExisting.getUsername());
        userEntityExisting.setPassword(userDto.getPassword() != null ? passwordEncoder.encode(userDto.getPassword()) : userEntityExisting.getPassword());
        userEntityExisting.setActive(userDto.getActive() != null ? userDto.getActive() : userEntityExisting.getActive());

        if (userDto.getRole() != null) {
            Role role = roleRepository.findByName(userDto.getRole())
                    .orElseThrow(() -> new RoleNotFoundException("Role not found"));


            userEntityExisting.setRole(role);
        }


        return userRepository.save(userEntityExisting);
    }

    @Override
    public void deleteUser(Integer id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        userEntity.setActive(false);
        userRepository.save(userEntity);
    }




}
