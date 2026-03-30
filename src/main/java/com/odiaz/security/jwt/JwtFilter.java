package com.odiaz.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.boot.model.source.internal.hbm.AttributesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter

public class JwtFilter extends OncePerRequestFilter {

    @Autowired

    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // obtenemos el token del header
        String token = getJwtFromRequest(request);


        try {
            if (token != null && jwtUtil.validateToken(token)) {

                // si el token es valido establecer la autenticacion

                String username = jwtUtil.getUsernameFromToken(token);

                // Aquí se puede extraer también los roles si se requiere

                Collection<GrantedAuthority> authorities = getAuthoritiesFromToken(token);


                // Autenticación en el contexto de seguridad de Spring
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);


                System.out.println("Authorities: " + authorities);


                SecurityContextHolder.getContext().setAuthentication(authentication);


            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException ex){

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired: " + ex.getMessage());
        }catch (JwtException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalid: " + e.getMessage());
        }

    }


    // metodo para obtener el token del header
    private String getJwtFromRequest(HttpServletRequest request) {


        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // metodo para obtener los roles del token
    private List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = jwtUtil.getClaimsFromToken(token);
        List<String> roles = (List<String>) claims.get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }


}
