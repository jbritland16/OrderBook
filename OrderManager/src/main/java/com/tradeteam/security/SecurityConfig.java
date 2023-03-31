package com.tradeteam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeConfig -> {
            authorizeConfig.requestMatchers("/login").permitAll();
            authorizeConfig.requestMatchers("/register").permitAll();
            authorizeConfig.anyRequest().authenticated();
        }).formLogin(form -> form.loginPage("/login").permitAll()).build();
    }
}