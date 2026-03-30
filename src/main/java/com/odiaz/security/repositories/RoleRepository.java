package com.odiaz.security.repositories;

import com.odiaz.security.entities.Role;
import com.odiaz.security.enums.RoleList;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<Role, Integer> {

   Optional<Role> findByName(String name);

    Optional<Role> findByName(RoleList name);



}
