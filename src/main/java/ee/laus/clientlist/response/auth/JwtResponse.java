package ee.laus.clientlist.response.auth;

import lombok.Data;

@Data
public class JwtResponse {
    private final String username;
    private final String token;
}
