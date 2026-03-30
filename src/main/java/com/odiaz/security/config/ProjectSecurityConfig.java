package com.odiaz.security.config;


import com.odiaz.security.exception.CustomAccessDeniedHandler;
import com.odiaz.security.exception.CustomAuthenticationEntryPoint;
import com.odiaz.security.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ProjectSecurityConfig {



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(request -> request
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/auth/login").permitAll()

                        .requestMatchers(HttpMethod.PUT,"/user/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/product/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/product/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/product/**").authenticated()

                        // Solo los ADMIN pueden eliminar productos
                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN")


                         //.requestMatchers("/product/**").hasAnyRole("admin", "seller")


        );


        //httpSecurity.formLogin(Customizer.withDefaults());
        //httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.exceptionHandling(exceptionHandler ->
                exceptionHandler.accessDeniedHandler(new CustomAccessDeniedHandler())

        );
        httpSecurity.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        );


        httpSecurity.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();


    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}