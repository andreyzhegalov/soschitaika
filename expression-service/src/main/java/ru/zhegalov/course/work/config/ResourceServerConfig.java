package ru.zhegalov.course.work.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    // @formatter:off
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .mvcMatcher("/api/expressions/**")
            .authorizeRequests()
            .mvcMatchers("/api/expressions/**").access("hasAuthority('SCOPE_expression.read')")
            .and()
            .csrf().disable()
            .oauth2ResourceServer()
            .jwt();
        return http.build();
    }
    // @formatter:on

}

