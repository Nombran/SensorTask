package by.bsuir.sensor.jwt;

import by.bsuir.sensor.user.User;
import by.bsuir.sensor.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(UserRole userRole) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRole.toString()));
        return authorities;
    }
}
