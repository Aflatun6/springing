package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class UserDetailsHashMapEncoded {
    private final Map<String,String> storage = new HashMap<>();
    private final PasswordEncoder enc;

    public UserDetailsHashMapEncoded(PasswordEncoder enc){
        this.enc = enc;
        storage.put("jim","123b");
        storage.put("john","456b");
    }
    // By commenting the @Bean annotation out , we just made this function meaningless
//    @Bean
    public UserDetailsService linkUsersToSpring(){
        Set<UserDetails> collected = storage.entrySet().stream()
                .map(this::mapper)
                .collect(Collectors.toSet());
        return new InMemoryUserDetailsManager(collected);
    }

    private UserDetails  mapper(Map.Entry<String, String> stringStringEntry) {
        return User
                .withUsername(stringStringEntry.getKey())
                .password(stringStringEntry.getValue())
                .passwordEncoder(enc::encode)
                .roles()
                .build();
    }
}
