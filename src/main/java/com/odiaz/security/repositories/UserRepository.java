package com.odiaz.security.repositories;

import com.odiaz.security.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username AND u.active = :active")
    Optional<UserEntity> findByUsernameActive(@Param("username") String username, @Param("active") Boolean active);


    @Query("SELECT u FROM UserEntity u WHERE u.active = true")
    List<UserEntity> findAllActiveUsers();

    Optional<UserEntity> findByUsername(String username);
}




