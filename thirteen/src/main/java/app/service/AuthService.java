package app.service;

import app.jwt.Const;
import app.entity.sec.XUserDetails;
import app.entity.db.XUser;
import app.jwt.JwtService;
import app.repo.XUserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final XUserRepo repo;
    private final PasswordEncoder enc;
    private final AuthenticationManager am;
    private final JwtService jwt;

    public AuthService(XUserRepo repo, PasswordEncoder enc, AuthenticationManager am, JwtService jwt) {
        this.repo = repo;
        this.enc = enc;
        this.am = am;
        this.jwt = jwt;
    }

    public boolean register(String username, String password) {
        Optional<XUser> found = repo.findByUsername(username);
        if (!found.isPresent()) {
            repo.save(new XUser(username, enc.encode(password), "User"));
        }
        return !found.isPresent();
    }

    public Optional<String> login(String username, String password, Boolean rememberMe) {
        Authentication auth = am.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        return Optional.of(auth)
                .filter(Authentication::isAuthenticated)
                .map(a -> { SecurityContextHolder.getContext().setAuthentication(a); return a;})
                .map(a -> (XUserDetails) a.getPrincipal())
                .map(XUserDetails::getId)
                .map(id -> jwt.generateToken(id,rememberMe));
//                .map(t-> Const.AUTH_BEARER + t);
    }
}
