package com.ctd.integrador.backend1.security.config;

import com.ctd.integrador.backend1.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.cors().and().csrf().disable().authorizeRequests().and()
               .httpBasic()
               .and().authorizeRequests()
               .antMatchers("/").permitAll()
               .antMatchers("/h2-console/*").hasRole("ADMIN")
               .antMatchers("/user").hasAnyRole("ADMIN", "USER")
               .antMatchers("/admin").hasRole("ADMIN")
               .antMatchers("/appointments").hasAnyRole("ADMIN", "USER")
               .antMatchers("/appointments/*").hasAnyRole("ADMIN", "USER")
               .anyRequest().hasRole("ADMIN")
               .and().formLogin()
               .and().logout();
        http.headers().frameOptions().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

}
