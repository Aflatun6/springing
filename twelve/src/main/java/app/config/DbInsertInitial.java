package app.config;

import app.entity.db.XUser;
import app.repo.XUserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class DbInsertInitial {

    private final XUserRepo repo;
    private final PasswordEncoder enc;


    public DbInsertInitial(XUserRepo repo, PasswordEncoder enc) {
        this.repo = repo;
        this.enc = enc;
    }

    public void create(){
        repo.saveAll(Arrays.asList(
                new XUser("jim",enc.encode("123c"),"Admin","User"),
                new XUser("john",enc.encode("456c"),"User")
        ));
    }
}
