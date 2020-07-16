package app.config;

import app.config.DbInsertInitial;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@Log4j2
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    public MySecurityConfig(DbInsertInitial initial){
        log.info("::: initiating Xusers");
        initial.create();
        log.info("::: Xusers have been created");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/","guest").permitAll()
                .antMatchers("/home").authenticated()
                .antMatchers("/admin").hasRole("Admin")
                .antMatchers("/me").hasRole("User")
                .antMatchers("/news").hasAnyRole("Admin","User");
        http.formLogin().loginPage("/login").permitAll();

    }
}
