package by.bsuir.sensor.jwt;

import by.bsuir.sensor.user.User;
import by.bsuir.sensor.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public JwtUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findByLogin(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        User user = optionalUser.get();
        return JwtUserFactory.create(user);
    }
}
