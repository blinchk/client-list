package ee.laus.clientlist.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAuthUtilTest {

    @Test
    void decode() {
        final String header = "Basic YW55OmFueQ==";
        BasicAuthUtil.Credentials excepted = new BasicAuthUtil.Credentials("any", "any");
        BasicAuthUtil.Credentials actual = BasicAuthUtil.decode(header);
        assertEquals(excepted.getUsername(), actual.getUsername());
        assertEquals(excepted.getPassword(), actual.getPassword());
    }

    @Test
    void decode_whenHeaderIsNull_throwsException() {
        final String header = null;
        assertThrows(IllegalArgumentException.class, () -> BasicAuthUtil.decode(header));
    }

    @Test
    void decode_whenHeaderIsEmpty_throwsException() {
        final String header = "";
        assertThrows(IllegalArgumentException.class, () -> BasicAuthUtil.decode(header));
    }

    @Test
    void decode_whenHeaderIsBlank_throwsException() {
        final String header = "      ";
        assertThrows(IllegalArgumentException.class, () -> BasicAuthUtil.decode(header));
    }

    @Test
    void decode_whenHeaderIsTooShort_throwsException() {
        final String header = "abc";
        assertThrows(IllegalArgumentException.class, () -> BasicAuthUtil.decode(header));
    }

    @Test
    void decode_whenHeaderDoesNotContainBasic_throwsException() {
        final String header = "Twice YW55OmFueQ==";
        assertThrows(IllegalArgumentException.class, () -> BasicAuthUtil.decode(header));
    }
}