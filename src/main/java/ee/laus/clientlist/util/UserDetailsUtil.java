package ee.laus.clientlist.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserDetailsUtil {
    private final static String DEFAULT_ROLE = "USER";
    public final static SimpleGrantedAuthority DEFAULT_AUTHORITY = new SimpleGrantedAuthority(DEFAULT_ROLE);
}
