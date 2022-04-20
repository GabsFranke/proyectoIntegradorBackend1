package com.ctd.integrador.backend1.login.security;

import com.ctd.integrador.backend1.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
               .httpBasic()
               .and().authorizeRequests()
               .antMatchers("/").permitAll()
               .antMatchers("/h2-console/*").hasRole("ADMIN")
               .antMatchers("/user").hasAnyRole("ADMIN", "USER")
               .antMatchers("/admin").hasRole("ADMIN")
               .antMatchers("/turnos").hasAnyRole("ADMIN", "USER")
               .antMatchers("/turnos/*").hasAnyRole("ADMIN", "USER")
               .anyRequest().hasRole("ADMIN")
               .and().formLogin()
               .and().logout();
       // Esto es para que no se rompa la consola de h2
       http.headers().frameOptions().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Deprecado, no compila.
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return NoOpPasswordEncoder.getInstance();
//        }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

}
