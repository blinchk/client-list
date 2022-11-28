package ee.laus.clientlist.support;

import ee.laus.clientlist.config.JwtConfig;
import ee.laus.clientlist.model.auth.JwtProperties;
import ee.laus.clientlist.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtSupportTest {
    @Spy
    @InjectMocks
    JwtSupport jwtSupport;
    @Mock
    JwtConfig jwtConfig;
    @Mock
    Authentication authentication;

    JwtProperties jwtProperties = new JwtProperties("signing-key", 15);

    @Test
    void generateToken() {
        MockedStatic<JwtTokenUtil> mocked = mockStatic(JwtTokenUtil.class);
        doReturn(jwtProperties).when(jwtSupport).getProperties();
        jwtSupport.generateToken(authentication);
        mocked.verify(() -> JwtTokenUtil.generate(any(), any()));
        mocked.close();
    }

    @Test
    void verify() {
        MockedStatic<JwtTokenUtil> mocked = mockStatic(JwtTokenUtil.class);
        doReturn(jwtProperties).when(jwtSupport).getProperties();
        jwtSupport.verify("anytoken");
        mocked.verify(() -> JwtTokenUtil.verify(any(), any()));
        mocked.close();
    }

    @Test
    void parseUsername() {
        MockedStatic<JwtTokenUtil> mocked = mockStatic(JwtTokenUtil.class);
        doReturn(jwtProperties).when(jwtSupport).getProperties();
        jwtSupport.parseUsername("anytoken");
        mocked.verify(() -> JwtTokenUtil.parseUsername(any(), any()));
        mocked.close();
    }

    @Test
    void getProperties() {
        jwtSupport.getProperties();
        Mockito.verify(jwtConfig).getProperties();
    }
}