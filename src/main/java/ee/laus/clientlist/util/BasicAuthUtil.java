package ee.laus.clientlist.util;


import ee.laus.clientlist.exception.UnauthorizedException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthUtil {
    private static final int HEADER_SUBSTRING_START_INDEX = "Basic".length();
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    public static final int USERNAME_INDEX = 0;
    public static final int PASSWORD_INDEX = 1;

    public static Credentials decode(String header) {
        if (header == null || header.isBlank() || header.isEmpty() || header.length() < HEADER_SUBSTRING_START_INDEX) {
            throw new UnauthorizedException();
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
