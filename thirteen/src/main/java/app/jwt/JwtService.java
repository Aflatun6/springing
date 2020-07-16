package app.jwt;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Log4j2
@PropertySource("classpath:jwt.properties")
public class JwtService {
    @Value("${jwt.expiry.normal}")
    private Long normal;

    @Value("${jwt.expiry.remember}")
    private Long remember;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Integer id, boolean rememberMe) {
        Date now = new Date();
        long delta = rememberMe ? remember : normal;
        Date expiry = new Date(now.getTime() + delta);

        return Jwts.builder()
                .setSubject(id.toString())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Optional<Jws<Claims>> tokenToClaims(String token) {
        try {
            return Optional.of(Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token));
        } catch (SignatureException ex) {
            log.error("JWT: Invalid signature");
        } catch (MalformedJwtException ex) {
            log.error("JWT: Invalid token");
        } catch (ExpiredJwtException ex) {
            log.error("JWT: Expired token");
        } catch (UnsupportedJwtException ex) {
            log.error("JWT: Unsupported token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT: token is empty.");
        }
        return Optional.empty();
    }

    public int claimsToId(Jws<Claims> claims) {
        return Integer.parseInt(claims.getBody().getSubject());
    }

}
