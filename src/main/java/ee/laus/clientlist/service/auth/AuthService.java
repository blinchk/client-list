package ee.laus.clientlist.service.auth;

import ee.laus.clientlist.exception.UnauthorizedException;
import ee.laus.clientlist.model.User;
import ee.laus.clientlist.response.auth.AuthMeResponse;
import ee.laus.clientlist.response.auth.JwtResponse;
import ee.laus.clientlist.service.UserService;
import ee.laus.clientlist.support.JwtSupport;
import ee.laus.clientlist.util.BasicAuthHeaderUtil;
import ee.laus.clientlist.util.BasicTokenUtil;
import ee.laus.clientlist.util.UsernamePasswordCredentials;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtSupport jwtSupport;
    private final UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(AuthService.class);

    public JwtResponse authenticate(final String header) {
        try {
            BasicAuthHeaderUtil.validate(header);
            final byte[] basicToken = BasicAuthHeaderUtil.decode(header);
            UsernamePasswordCredentials credentials = BasicTokenUtil.extract(basicToken);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword()
                    ));
            setAuthenticationInContext(authentication);
            final String token = jwtSupport.generateToken(authentication);
            logger.info("User with username={} successfully authenticated with username and password",
                    credentials.getUsername());
            return new JwtResponse(credentials.getUsername(), token);
        } catch (AuthenticationException | IllegalArgumentException exception) {
            throw new UnauthorizedException("Authorization is failed", exception);
        }
    }

    public void setAuthenticationInContext(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public AuthMeResponse isLoggedIn() {
        final User user = userService.getCurrentUser();
        return new AuthMeResponse(
                true,
                user.getUsername()
        );
    }
}
