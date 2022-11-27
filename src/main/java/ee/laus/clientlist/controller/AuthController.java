package ee.laus.clientlist.controller;

import ee.laus.clientlist.response.auth.AuthMeResponse;
import ee.laus.clientlist.response.auth.JwtResponse;
import ee.laus.clientlist.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping(value = "/login")
    public JwtResponse login(HttpServletRequest request) {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return authService.authenticate(header);
    }

    @GetMapping(value = "/me")
    public AuthMeResponse isLoggedIn() {
        return authService.isLoggedIn();
    }
}
