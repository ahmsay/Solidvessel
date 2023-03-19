package com.solidvessel.auth.infra.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterConfig {

    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(antMatcher("/")).permitAll()
                .requestMatchers(antMatcher("/login")).permitAll()
                .requestMatchers(antMatcher("/signUp")).permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationManager(authenticationManager)
                .logout().disable()
                .formLogin().disable()
                .csrf().disable();
        return httpSecurity.build();
    }

    public Customizer<CsrfConfigurer<HttpSecurity>> configureCsrf() { // would be needed later
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName(null);
        return (csrf) -> csrf
                .csrfTokenRepository(tokenRepository)
                .csrfTokenRequestHandler(requestHandler);
    }
}
