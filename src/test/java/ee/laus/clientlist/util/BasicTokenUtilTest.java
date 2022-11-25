package ee.laus.clientlist.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class BasicTokenUtilTest {
    @Test
    void extract() {
        final byte[] token = "user:password".getBytes(StandardCharsets.UTF_8);
        UsernamePasswordCredentials excepted = new UsernamePasswordCredentials("user", "password");
        UsernamePasswordCredentials actual = BasicTokenUtil.extract(token);
        assertEquals(excepted.getUsername(), actual.getUsername());
        assertEquals(excepted.getPassword(), actual.getPassword());
    }
}