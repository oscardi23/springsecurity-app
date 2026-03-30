package com.odiaz.security.services.impl;

import com.odiaz.security.entities.Role;

import com.odiaz.security.repositories.RoleRepository;
import com.odiaz.security.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Optional<Role> findByName(String name) {

        return roleRepository.findByName(name);
    }

    @Override
    public Optional<Role> findById(Integer id)  {

            return roleRepository.findById(id);
    }

    @Override
    public Optional<Integer> findByIdRole(Integer id)  {

        return roleRepository.findById(id).map(Role::getId);

    }
}
