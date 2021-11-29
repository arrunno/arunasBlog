package com.arunas.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public/**", "/")
                    .permitAll()
                .antMatchers("/private/")
                    .authenticated()
                .anyRequest()
                    .authenticated()
                .and()
            .formLogin()
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String ona = delegatingPasswordEncoder.encode("ona");
        String petras = delegatingPasswordEncoder.encode("petras");
        String arunas = delegatingPasswordEncoder.encode("arunas");

        auth.inMemoryAuthentication()
                .withUser("ona@user.com")
                .password("{bcrypt}$2a$10$jUIZsuoTI4EQkTsVapCuN.yZcbu6baLkM4hp309il3KAz6QJwnjBO") // user
                .roles("USER")
                .and()
                .withUser("petras@user.com")
                .password("{bcrypt}$2a$10$Hy6IbcG73MjCtE02U103VOLAuZ1NFkEMxqrYUYdUkejsFIFh0Xdl2") // user
                .roles("USER")
                .and()
                .withUser("arunas@admin.com")
                .password("{bcrypt}$2a$10$JEU4DIXEyZ5ouJ5hIJ8sV.0C8/XAHvMJacRke//JI.Zyk4ufrRzl.") // admin
                .roles("USER", "ADMIN");

    }

}
