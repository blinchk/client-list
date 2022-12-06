package ee.laus.clientlist.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BearerAuthHeaderUtil {
    private static final String HEADER_AUTHORIZATION_TYPE = "Bearer";
    protected static final int HEADER_SUBSTRING_START_INDEX = HEADER_AUTHORIZATION_TYPE.length();

    public static String extract(final String header) {
        if (!containsValidAuthorizationType(header)) {
            throw new IllegalArgumentException("Header must contain authorization type");
        }
        return header.substring(HEADER_SUBSTRING_START_INDEX).trim();
    }

    public static boolean containsValidAuthorizationType(String header) {
        return header.length() > HEADER_SUBSTRING_START_INDEX && header.contains(HEADER_AUTHORIZATION_TYPE);
    }
}
