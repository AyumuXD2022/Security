package com.example.seguridadGrand.config;

import com.example.seguridadGrand.auth.UsuarioAuthenticationProvider;
import com.example.seguridadGrand.jwt.JWTTokeGenerator;
import com.example.seguridadGrand.jwt.ValidatorJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ConfigSecurity {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.cors().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterAfter(new JWTTokeGenerator(), BasicAuthenticationFilter.class)
                .addFilterBefore(new ValidatorJWT(), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/api/user").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/api/admin").hasRole("ADMIN")
                .requestMatchers("/api/login").authenticated()
                .requestMatchers(HttpMethod.POST,"/api/register").permitAll();


        http.httpBasic();
        return http.build();
    }





}
