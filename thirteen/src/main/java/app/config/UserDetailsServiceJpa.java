package app.config;


import app.entity.sec.XUserDetails;
import app.entity.db.XUser;
import app.repo.XUserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
@Configuration
public class UserDetailsServiceJpa implements UserDetailsService {

    private final XUserRepo repo;

    public UserDetailsServiceJpa(XUserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info(String.format("retrieving form database our user %s", s));

        return repo.findByUsername(s).map(UserDetailsServiceJpa::mapper_to_xUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User: %s isn't found in our DB", s)
                ));

    }

    public UserDetails loadUserByUserId(int id) {
        return repo.findById(id).map(UserDetailsServiceJpa::mapper_to_xUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User_id: %s isn't found in our DB", id)
                ));

    }


    public static UserDetails mapper_to_xUserDetails(XUser xuser) {
        return new XUserDetails(
                xuser.getId(),
                xuser.getUsername(),
                xuser.getPassword(),
                xuser.getRoles()
        );
    }

}
