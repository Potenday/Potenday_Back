package com.example.protenday.config;

import com.example.protenday.service.UserEntityService;
import com.example.protenday.util.JwtTokenFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final String key;
    private final UserEntityService userEntityService;

    public JwtSecurityConfig(String key, UserEntityService userEntityService) {
        this.key = key;
        this.userEntityService = userEntityService;
    }

    @Override
    public void configure(HttpSecurity http) {

        http.addFilterBefore(
                new JwtTokenFilter(key, userEntityService),
                UsernamePasswordAuthenticationFilter.class
        );
    }

}
