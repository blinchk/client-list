package ee.laus.clientlist.config;

import ee.laus.clientlist.config.encoder.DummyPasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyPasswordEncoderTest {
    DummyPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new DummyPasswordEncoder();
    }

    @Test
    void encode() {
        final String password = "password";
        final String actual = passwordEncoder.encode(password);
        assertEquals(password, actual);
    }

    @Test
    void matches() {
        final String password = "password";
        final boolean actual = passwordEncoder.matches(password, password);
        assertTrue(actual);
    }
}