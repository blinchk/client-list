package ee.laus.clientlist.util;

public class BearerAuthHeaderUtil {
    private static final String HEADER_AUTHORIZATION_TYPE = "Bearer";
    protected static final int HEADER_SUBSTRING_START_INDEX = HEADER_AUTHORIZATION_TYPE.length();

    public static String extract(final String header) {
        if (!header.contains(HEADER_AUTHORIZATION_TYPE)) {
            throw new IllegalArgumentException("Header must contain authorization type");
        }
        return header.substring(HEADER_SUBSTRING_START_INDEX).trim();
    }
}
