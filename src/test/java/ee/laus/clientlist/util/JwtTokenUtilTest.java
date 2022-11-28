package ee.laus.clientlist.util;

import ee.laus.clientlist.model.auth.JwtProperties;
import ee.laus.clientlist.model.auth.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtTokenUtilTest {
    private static final String SIGNING_KEY = "random-key";
    private static final int EXPIRATION_TIME_IN_DAYS = 30;
    JwtProperties properties;
    @Mock
    Authentication authentication;

    @BeforeEach
    void setUp() {
        properties = new JwtProperties(SIGNING_KEY, EXPIRATION_TIME_IN_DAYS);
    }

    @Test
    void generate() {
        final String expectedSubject = "user";
        final long dayBeforeExpiration = EXPIRATION_TIME_IN_DAYS-1L;
        when(authentication.getPrincipal()).thenReturn(
                new UserDetailsImpl(3L, "user", "password"));
        String token = JwtTokenUtil.generate(authentication, properties);
        Jws<Claims> claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token);
        final DefaultClaims body = (DefaultClaims) claims.getBody();
        assertEquals(expectedSubject, body.getSubject());
        assertTrue(Timestamp.valueOf(LocalDateTime.now().plusDays(dayBeforeExpiration)).before(body.getExpiration()));
    }

    @Test
    void validate() {
        when(authentication.getPrincipal()).thenReturn(
                new UserDetailsImpl(3L, "user", "password"));
        final String token = JwtTokenUtil.generate(authentication, properties);
        boolean actual = JwtTokenUtil.verify(token, properties);
        assertTrue(actual);
    }

    @Test
    void validate_throwsException() {
        final String token = null;
        assertThrows(IllegalArgumentException.class, () ->
                JwtTokenUtil.validate(token));
    }
}