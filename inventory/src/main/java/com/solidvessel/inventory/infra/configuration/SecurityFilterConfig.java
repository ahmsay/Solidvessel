package com.solidvessel.inventory.infra.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityFilterConfig {

    @Bean
    public SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(antMatcher("/")).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().disable()
                .formLogin().disable()
                .csrf().disable();
        return httpSecurity.build();
    }
}