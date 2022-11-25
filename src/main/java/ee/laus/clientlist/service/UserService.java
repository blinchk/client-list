package ee.laus.clientlist.service;

import ee.laus.clientlist.exception.UnauthorizedException;
import ee.laus.clientlist.model.User;
import ee.laus.clientlist.model.auth.UserDetailsImpl;
import ee.laus.clientlist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final User user = repository.findById(userDetails.getId()).orElseThrow(UnauthorizedException::new);
        return user;
    }
}
