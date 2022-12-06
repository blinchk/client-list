package ee.laus.clientlist.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BearerAuthHeaderUtilTest {

    @Test
    void extract() {
        final String header = "Bearer aaa";
        assertDoesNotThrow(() -> BearerAuthHeaderUtil.extract(header));
    }

    @Test
    void extract_throwsException() {
        final String header = "aaa";
        assertThrows(IllegalArgumentException.class, () -> BearerAuthHeaderUtil.extract(header));
    }
}