package ee.laus.clientlist.util;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthUtil {
    private static final String HEADER_AUTHORIZATION_TYPE = "Basic";
    private static final int HEADER_SUBSTRING_START_INDEX = HEADER_AUTHORIZATION_TYPE.length();
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    public static final int USERNAME_INDEX = 0;
    public static final int PASSWORD_INDEX = 1;

    public static Credentials decode(String header) throws IllegalArgumentException {
        if (header == null || header.isBlank() || header.isEmpty()) {
            throw new IllegalArgumentException("Header must be not null, not empty and not blank");
        }
        if (header.length() < HEADER_SUBSTRING_START_INDEX || !header.contains(HEADER_AUTHORIZATION_TYPE)) {
            throw new IllegalArgumentException("Header must be related to Basic authorization");
        }
        String base64Credentials = header.substring(HEADER_SUBSTRING_START_INDEX).trim();
        byte[] decodedCredentialsBytes = Base64.getDecoder().decode(base64Credentials);
        String[] credentials = new String(decodedCredentialsBytes, DEFAULT_CHARSET).split(":");
        return new Credentials(credentials[USERNAME_INDEX], credentials[PASSWORD_INDEX]);
    }

    @RequiredArgsConstructor
    @Getter
    public static class Credentials {
        private final String username;
        private final String password;
    }
}
