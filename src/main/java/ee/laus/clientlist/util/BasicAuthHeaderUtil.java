package ee.laus.clientlist.util;

import lombok.experimental.UtilityClass;

import java.util.Base64;

@UtilityClass
public class BasicAuthHeaderUtil {
    private static final String HEADER_AUTHORIZATION_TYPE = "Basic";
    protected static final int HEADER_SUBSTRING_START_INDEX = HEADER_AUTHORIZATION_TYPE.length();

    public static void validate(final String header) throws IllegalArgumentException {
        if (header == null || header.isBlank() || header.isEmpty()) {
            throw new IllegalArgumentException("Header must be not null, not empty and not blank");
        }
        if (!containsValidAuthorizationType(header)) {
            throw new IllegalArgumentException("Header must be related to Basic authorization");
        }
    }

    public static boolean containsValidAuthorizationType(String header) {
        return header.length() > HEADER_SUBSTRING_START_INDEX && header.contains(HEADER_AUTHORIZATION_TYPE);
    }

    public static byte[] decode(final String header) {
        return Base64.getDecoder().decode(
                header.substring(HEADER_SUBSTRING_START_INDEX).trim()
        );
    }
}
