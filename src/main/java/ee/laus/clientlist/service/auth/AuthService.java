package ee.laus.clientlist.service.auth;

import ee.laus.clientlist.config.JwtConfig;
import ee.laus.clientlist.exception.UnauthorizedException;
import ee.laus.clientlist.model.auth.JwtProperties;
import ee.laus.clientlist.response.auth.JwtResponse;
import ee.laus.clientlist.util.BasicAuthUtil;
import ee.laus.clientlist.util.JwtAuthUtil;
import lombok.RequiredArgsConstructor;
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
    private final JwtConfig jwtConfig;

    public JwtResponse authenticate(String header) {
        BasicAuthUtil.Credentials credentials = BasicAuthUtil.decode(header);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            JwtProperties properties = jwtConfig.getProperties();
            final String token = JwtAuthUtil.generateToken(authentication, properties);
            return new JwtResponse(credentials.getUsername(), token);
        } catch (AuthenticationException e) {
            throw new UnauthorizedException();
        }
    }
}
