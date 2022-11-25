package ee.laus.clientlist.util;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BasicTokenUtil {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    public static final int USERNAME_INDEX = 0;
    public static final int PASSWORD_INDEX = 1;
    private static final int REQUIRED_SPLIT_CREDENTIALS_PARTS_COUNT = 2;

    public static UsernamePasswordCredentials extract(final byte[] token) throws IllegalArgumentException {
        String[] credentials = new String(token, DEFAULT_CHARSET).split(":");
        if (credentials.length != REQUIRED_SPLIT_CREDENTIALS_PARTS_COUNT) {
            throw new IllegalArgumentException("Credentials must contain exactly two parts");
        }
        return new UsernamePasswordCredentials(credentials[USERNAME_INDEX],
                credentials[PASSWORD_INDEX]);
    }

}
