package com.odiaz.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;


@Getter
@Setter
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;


    // generar token JWT
    public String generateToken(Authentication authentication) {

        // obtener el usuario autenticado
        UserDetails mainUser = (UserDetails) authentication.getPrincipal();
        //usamos  la clave secreta  para crear el token
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        //generamos el token
        return Jwts.builder()
                .setSubject(mainUser.getUsername())   // El nombre de usuario como 'subject'
                .claim("roles", mainUser.getAuthorities().stream().map(GrantedAuthority::getAuthority) // Extraemos los roles y los agregamos como 'claims'
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L)) // Fecha de expiración
                .signWith(key) // Firmamos el token con la clave secreta
                .compact()


                ;
    }


    // obtene los claims del token
    public Claims getClaimsFromToken(String token) {

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        // parsear el token y obtener los claims

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    // validar el token
    public boolean validateToken(String token) {


        try {
            // obtenemos los claims del token y verificamos la fecha de expiracion
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();

            return expiration.after(new Date());
        } catch (Exception e) {
            return false; // si ocurre una excepcion el token es invalido
        }
    }

    // obtener el nombre de usuario del token
    public String getUsernameFromToken(String token) {

        Claims claims = getClaimsFromToken(token);
        return claims.getSubject(); //delvolvemos el subject que es el nombre de usuario
    }
}
