package ee.laus.clientlist.controller;

import ee.laus.clientlist.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @InjectMocks
    AuthController authController;

    @Mock
    HttpServletRequest request;

    @Mock
    AuthService authService;

    @Test
    void login() {
        final String headerValue = "Basic YW55OmFueQ==";
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(headerValue);
        authController.login(request);
        verify(authService).authenticate(headerValue);
    }

    @Test
    void isLoggedIn() {
        authController.isLoggedIn();
        verify(authService).isLoggedIn();
    }
}