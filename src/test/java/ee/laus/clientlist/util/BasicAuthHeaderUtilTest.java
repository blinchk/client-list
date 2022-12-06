package ee.laus.clientlist.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAuthHeaderUtilTest {

    void validate(String header) {
        BasicAuthHeaderUtil.validate(header);
    }

    @Test
    void validate_successfully() {
        final String header = "Basic YW55OmFueQ==";
        assertDoesNotThrow(() -> validate(header));
    }

    @Test
    void validate_whenHeaderIsNull_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validate(null));
    }

    @Test
    void validate_whenHeaderIsEmpty_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validate(""));
    }

    @Test
    void validate_whenHeaderIsBlank_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validate("     "));
    }

    @Test
    void validate_whenHeaderIsTooShort_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validate("abc"));
    }

    @Test
    void decode_whenHeaderDoesNotContainBasic_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validate("Twice YW55OmFueQ=="));
    }
}