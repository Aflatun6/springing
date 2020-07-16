package app.config;

import app.jwt.JwtFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Log4j2
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    public MySecurityConfig(DbInsertInitial initial, JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
        log.info("::: initiating Xusers");
        initial.create();
        log.info("::: Xusers have been created");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //  DO NOT KNOW WHAT IS IT

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // NO SESSION WILL BE

        http
                .authorizeRequests()
                .antMatchers("/auth/reg", "/auth/login").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
