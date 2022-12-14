package ee.laus.clientlist.model.auth;

import lombok.Data;

@Data
public class JwtProperties {
    private final String signingKey;
    private final int expirationTimeInDays;
}
