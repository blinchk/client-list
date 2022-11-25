package ee.laus.clientlist.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAuthHeaderUtilTest {
    @Test
    void validate() {
        final String header = "Basic YW55OmFueQ==";
        assertDoesNotThrow(() -> BasicAuthHeaderUtil.validate(header));
    }

    @Test
    void validate_whenHeaderIsNull_throwsException() {
        final String header = null;
        assertThrows(IllegalArgumentException.class, () -> BasicAuthHeaderUtil.validate(header));
    }

    @Test
    void validate_whenHeaderIsEmpty_throwsException() {
        final String header = "";
        assertThrows(IllegalArgumentException.class, () -> BasicAuthHeaderUtil.validate(header));
    }

    @Test
    void decode_whenHeaderIsBlank_throwsException() {
        final String header = "      ";
        assertThrows(IllegalArgumentException.class, () -> BasicAuthHeaderUtil.validate(header));
    }

    @Test
    void decode_whenHeaderIsTooShort_throwsException() {
        final String header = "abc";
        assertThrows(IllegalArgumentException.class, () -> BasicAuthHeaderUtil.validate(header));
    }

    @Test
    void decode_whenHeaderDoesNotContainBasic_throwsException() {
        final String header = "Twice YW55OmFueQ==";
        assertThrows(IllegalArgumentException.class, () -> BasicAuthHeaderUtil.validate(header));
    }
}