package com.ingressaca.bookstoretask.security.config;

import com.ingressaca.bookstoretask.security.filter.AuthTokenFilter;
import com.ingressaca.bookstoretask.security.service.SecurityUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthTokenFilter  authTokenFilter;

    private final EntryPointConfig entryPointConfig;

    private final SecurityUserService securityUserService;

    private final PasswordEncoder passwordEncoder;


    public SecurityConfig(AuthTokenFilter authTokenFilter, EntryPointConfig entryPointConfig, SecurityUserService securityUserService, PasswordEncoder passwordEncoder) {
        this.authTokenFilter = authTokenFilter;
        this.entryPointConfig = entryPointConfig;
        this.securityUserService = securityUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(securityUserService).passwordEncoder(passwordEncoder);
    }


    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/h2-console").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(entryPointConfig);
    }
}
