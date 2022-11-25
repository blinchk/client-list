package ee.laus.clientlist.service.auth;

import ee.laus.clientlist.response.auth.JwtResponse;
import ee.laus.clientlist.support.JwtSupport;
import ee.laus.clientlist.util.BasicTokenUtil;
import ee.laus.clientlist.util.UsernamePasswordCredentials;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    private static final Authentication authentication = new UsernamePasswordAuthenticationToken(null, null);

    @Spy
    @InjectMocks
    AuthService authService;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtSupport jwtSupport;

    @Test
    void authenticate() {
        final String username = "user";
        final String header = "Basic dXNlcjpwYXNzd29yZA==";
        final String token = "any.token";
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtSupport.generateToken(authentication)).thenReturn(token);
        doNothing().when(authService).setAuthenticationInContext(authentication);
        MockedStatic<BasicTokenUtil> mockedBasicAuthUtil = mockStatic(BasicTokenUtil.class);
        mockedBasicAuthUtil.when(() -> BasicTokenUtil.extract(any()))
                .thenReturn(new UsernamePasswordCredentials(username, "password"));
        JwtResponse actual = authService.authenticate(header);
        mockedBasicAuthUtil.close();
        assertEquals(username, actual.getUsername());
        assertEquals(token, actual.getToken());
    }
}