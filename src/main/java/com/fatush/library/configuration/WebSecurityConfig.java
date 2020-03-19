package com.fatush.library.configuration;

import com.fatush.library.handlers.CustomAuthenticationSuccessHandler;
import com.fatush.library.handlers.CustomLogoutSuccessHandler;
import com.fatush.library.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService, CustomAuthenticationSuccessHandler authenticationSuccessHandler, CustomLogoutSuccessHandler customLogoutSuccessHandler) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.customAuthenticationSuccessHandler = authenticationSuccessHandler;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/book", "/book/*", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf()
                .disable();

        http
                .formLogin()
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll();

        http
                .logout().permitAll()
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .logoutSuccessUrl("/login");
    }

    /*  UsernamePasswordAuthenticationFilter*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}