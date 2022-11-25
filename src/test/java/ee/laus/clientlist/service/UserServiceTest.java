package ee.laus.clientlist.service;

import ee.laus.clientlist.exception.UnauthorizedException;
import ee.laus.clientlist.model.User;
import ee.laus.clientlist.model.auth.UserDetailsImpl;
import ee.laus.clientlist.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    SecurityContext securityContext;
    @Mock
    Authentication authentication;
    UserDetails userDetails;
    User user;

    @BeforeEach
    void setUp() {
        userDetails = new UserDetailsImpl(4L, "username", "password");
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        this.user = new User();
        user.setId(5L);
    }

    @Test
    void getCurrentUser() {
        MockedStatic<SecurityContextHolder> mocked = mockStatic(SecurityContextHolder.class);
        mocked.when(SecurityContextHolder::getContext).thenReturn(securityContext);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        userService.getCurrentUser();
        mocked.close();
    }

    @Test
    void getCurrentUser_throwsException_whenOptionalIsEmpty() {
        MockedStatic<SecurityContextHolder> mocked = mockStatic(SecurityContextHolder.class);
        mocked.when(SecurityContextHolder::getContext).thenReturn(securityContext);
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(UnauthorizedException.class, () -> {
            userService.getCurrentUser();
            mocked.close();
        });
    }
}