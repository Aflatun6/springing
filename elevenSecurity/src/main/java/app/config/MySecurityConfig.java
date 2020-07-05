package app.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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





//    @Bean  NOW USERDETAILSHASHMAP WILL WORK, THESE IS   @BEAN ANNOTION WILL REPLACE THIS THING
    // WITHOUT @BEAN  THE FUNCTION IS INVISIBLE FOR SPRING
    @Override
    protected UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(Arrays.asList(
                User.withDefaultPasswordEncoder().username("jim").password("123").roles().build(),
                User.withDefaultPasswordEncoder().username("john").password("456").roles().build()
        )
        );
    }
}
