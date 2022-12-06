package ee.laus.clientlist.util;

import ee.laus.clientlist.exception.JwtValidationException;
import ee.laus.clientlist.model.auth.JwtProperties;
import io.jsonwebtoken.*;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@UtilityClass
public class JwtTokenUtil {

    public static final SignatureAlgorithm DEFAULT_ALGORITHM = SignatureAlgorithm.HS512;

    public static String generate(Authentication authentication, JwtProperties properties) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusDays(properties.getExpirationTimeInDays());
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(expirationTime))
                .signWith(DEFAULT_ALGORITHM, properties.getSigningKey())
                .compact();
    }

    public static boolean verify(String token, JwtProperties properties) throws JwtValidationException {
        parse(token, properties);
        return true;
    }

    public static String parseUsername(String token, JwtProperties properties) throws JwtValidationException {
        return parse(token, properties).getBody().getSubject();
    }

    private static Jws<Claims> parse(String token, JwtProperties properties) {
        try {
            return Jwts.parser()
                    .setSigningKey(properties.getSigningKey())
                    .parseClaimsJws(token);
        } catch (SignatureException exception) {
            throw new JwtValidationException("Invalid JWT signature", exception);
        } catch (MalformedJwtException exception) {
            throw new JwtValidationException("Invalid JWT token", exception);
        } catch (ExpiredJwtException exception) {
            throw new JwtValidationException("JWT token is expired", exception);
        } catch (UnsupportedJwtException exception) {
            throw new JwtValidationException("JWT token is unsupported", exception);
        } catch (IllegalArgumentException exception) {
            throw new JwtValidationException("JWT claims is empty string", exception);
        }
    }

    public static void validate(final String token) throws IllegalArgumentException {
        if (token == null || token.isEmpty() || token.isBlank()) {
            throw new IllegalArgumentException("JWT token cannot be null");
        }
    }
}
