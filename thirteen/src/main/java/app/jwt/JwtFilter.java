package app.jwt;

import app.config.UserDetailsServiceJpa;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsServiceJpa uds;
    private final JwtService jwt;

    public JwtFilter(UserDetailsServiceJpa uds, JwtService jwt) {
        this.uds = uds;
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        try {
            extractToken(req)
                    .flatMap(jwt::tokenToClaims)
                    .map(jwt::claimsToId)
                    .map(uds::loadUserByUserId)
                    .map(ud -> new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities()))
                    .ifPresent(auth -> {
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    });

            chain.doFilter(req, res);
        } catch (Exception x) {
            log.error(x.getMessage());
        }
    }

    private Optional<String> extractToken(HttpServletRequest req) {
        String auth = req.getHeader(Const.HEADER);
        return Optional.ofNullable(auth);
//                .filter(a -> a.startsWith(Const.AUTH_BEARER))
//                .map(a -> a.substring(Const.AUTH_BEARER.length()));
    }
}
