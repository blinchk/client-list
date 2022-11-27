package ee.laus.clientlist.response.auth;

import lombok.Data;

@Data
public class AuthMeResponse {
    private final boolean loggedIn;
    private final String username;
}
