package com.odiaz.security.controller;


import com.odiaz.security.dtos.AuthRequest;
import com.odiaz.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {


        try{
            Authentication authentication = authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(

                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            String jwt = jwtUtil.generateToken(authentication);

            return ResponseEntity.ok(jwt);

        }catch (AuthenticationException ex) {

            return ResponseEntity.status(401).body("Login failed: " + ex.getMessage());
        }


    }

}
