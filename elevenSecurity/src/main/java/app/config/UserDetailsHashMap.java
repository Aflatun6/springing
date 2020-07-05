package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class UserDetailsHashMap {
    private final Map<String,String> storage = new HashMap<>();

    public UserDetailsHashMap(){
        storage.put("jim","123a");
        storage.put("john","456a");
    }
//    By taking away the @BEAN ANNOTATION we make this functin unvisible for Spring
//    @Bean
    public UserDetailsService linkUsersToSpring(){
        Set<UserDetails> collected = storage.entrySet().stream()
                .map(this::mapper)
                .collect(Collectors.toSet());
        return new InMemoryUserDetailsManager(collected);
    }

    private UserDetails  mapper(Map.Entry<String, String> stringStringEntry) {
        return User.withDefaultPasswordEncoder().username(stringStringEntry.getKey()).password(stringStringEntry.getValue()).roles().build();
    }
}
