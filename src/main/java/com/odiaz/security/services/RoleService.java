package com.odiaz.security.services;

import com.odiaz.security.entities.Role;


import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(String name) ;
    Optional<Role> findById( Integer id) ;

    Optional<Integer> findByIdRole(Integer id);
}
