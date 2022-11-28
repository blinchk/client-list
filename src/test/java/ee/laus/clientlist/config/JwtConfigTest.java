package ee.laus.clientlist.config;

import ee.laus.clientlist.model.auth.JwtProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class JwtConfigTest {
    @InjectMocks
    JwtConfig jwtConfig;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtConfig, "signingKey", "signing-key");
        ReflectionTestUtils.setField(jwtConfig, "expirationInDays", 15);
    }

    @Test
    void getProperties() {
        final String expectedSigningKey = "signing-key";
        final int expectedExpirationInDays = 15;
        JwtProperties properties = jwtConfig.getProperties();
        assertEquals(expectedSigningKey, properties.getSigningKey());
        assertEquals(expectedExpirationInDays, properties.getExpirationTimeInDays());
    }
}