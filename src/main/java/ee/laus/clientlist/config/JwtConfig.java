package ee.laus.clientlist.config;

import ee.laus.clientlist.model.auth.JwtProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {
    @Value("${jwt.auth.secret}")
    private String secret;
    @Value("${jwt.auth.expiration-time-in-days}")
    private int expirationInDays;

    public JwtProperties getProperties() {
        return new JwtProperties(secret, expirationInDays);
    }
}
