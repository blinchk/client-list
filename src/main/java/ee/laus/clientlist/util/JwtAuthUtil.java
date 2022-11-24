package ee.laus.clientlist.util;

import ee.laus.clientlist.model.auth.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JwtAuthUtil {

    public static final SignatureAlgorithm DEFAULT_ALGORITHM = SignatureAlgorithm.HS512;

    public static String generateToken(Authentication authentication, JwtProperties properties) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusDays(properties.getExpirationTimeInDays());
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(expirationTime))
                .signWith(DEFAULT_ALGORITHM, properties.getSecret())
                .compact();
    }
}
