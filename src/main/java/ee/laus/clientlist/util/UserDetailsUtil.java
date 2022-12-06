package ee.laus.clientlist.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@UtilityClass
public class UserDetailsUtil {
    private static final String DEFAULT_ROLE = "USER";
    public static final SimpleGrantedAuthority DEFAULT_AUTHORITY = new SimpleGrantedAuthority(DEFAULT_ROLE);
}
