package com.odiaz.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Arrays;



public class JwtTest {

    public static void main(String[] args) {


        JwtUtil jwtUtil = new JwtUtil();

        jwtUtil.setSecret("miSuperClaveSecretaSeguraQueDebeSerLarga123456");
        jwtUtil.setExpiration(3600);


        // crear un usuario simulado con roles

        User user = new User("testUser", "testPassword",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
                                new SimpleGrantedAuthority("ROLE_ADMIN")

                        ));

        // crea un objeto de autenticacion simulado

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        // generar el token JWT
        String token = jwtUtil.generateToken(authenticationToken);
        System.out.println("Token generado: " + token);


        //validar toquen
        boolean isValid = jwtUtil.validateToken(token);
        System.out.println("Token valido: " + isValid);

        // obtener el nombre de usuario del token
        String username = jwtUtil.getUsernameFromToken(token);
        System.out.println("Nombre de usuario del token: " + username);

    }
}
