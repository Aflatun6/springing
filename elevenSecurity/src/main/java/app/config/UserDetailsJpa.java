package app.config;

import app.entity.db.XUser;
import app.repo.XUserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class UserDetailsJpa {

    private final XUserRepo repo;

    public UserDetailsJpa(XUserRepo repo) {
        this.repo = repo;
    }

    @Bean
    public UserDetailsService tookFromDBandAddToTheSpring(){
        Set<UserDetails> collected = repo.findAll().stream().map(this::mapper).collect(Collectors.toSet());

        return new InMemoryUserDetailsManager(collected);
    }

    private UserDetails mapper(XUser xUser) {
        return User.withUsername(xUser.getUsername())
                .password(xUser.getPassword())
                .roles()
                .build();
    }

}
