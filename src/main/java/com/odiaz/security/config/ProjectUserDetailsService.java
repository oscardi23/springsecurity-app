package com.odiaz.security.config;

import com.odiaz.security.entities.UserEntity;
import com.odiaz.security.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor

@Service
public class ProjectUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsernameActive(username, Boolean.TRUE).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User not found %s", username)));
        GrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole().getName().toString());


        return new User(userEntity.getUsername(), userEntity.getPassword(), List.of(authority));
    }
}
