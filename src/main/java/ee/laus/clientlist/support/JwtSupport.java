package ee.laus.clientlist.support;

import ee.laus.clientlist.config.JwtConfig;
import ee.laus.clientlist.exception.JwtValidationException;
import ee.laus.clientlist.model.auth.JwtProperties;
import ee.laus.clientlist.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtSupport {
    private final JwtConfig config;

    public String generateToken(Authentication authentication) {
        return JwtTokenUtil.generate(authentication, getProperties());
    }

    public boolean verify(String token) throws JwtValidationException {
        return JwtTokenUtil.verify(token, getProperties());
    }

    public String parseUsername(String token) {
        return JwtTokenUtil.parseUsername(token, getProperties());
    }

    private JwtProperties getProperties() {
        return config.getProperties();
    }
}
