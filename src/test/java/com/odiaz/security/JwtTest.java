package com.odiaz.security;


import com.odiaz.security.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtUtil jwtUtil;


    @Test
    void testGenerateAndValidateToken() {

        // preparacion del usuario simulado

        User user = new User("testUser", "testPassword",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_ADMIN")
                ));


        // creacion del objeto de autenticacion simulado

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        // generar el token JWT
        String token = jwtUtil.generateToken(authenticationToken);
        System.out.println("Token generado: " + token);


        //validar toquen
        boolean isValid = jwtUtil.validateToken(token);
        System.out.println("Token valido: " + isValid);

        // verificar que el token es valido
       assertTrue(isValid, " El token no es valido");


        // obtener el nombre de usuario del token
        String username = jwtUtil.getUsernameFromToken(token);
        System.out.println("Nombre de usuario del token: " + username);

        // verificar que el nombre de usuario es correcto
        assertEquals("testUser", username, "El nombre de usuario no es correcto");


    }


}
