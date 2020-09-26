package by.bsuir.sensor.authentication;

import by.bsuir.sensor.jwt.JwtTokenProvider;
import by.bsuir.sensor.user.User;
import by.bsuir.sensor.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDao userDao;
    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                                 JwtTokenProvider jwtTokenProvider,
                                 UserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDao = userDao;
    }

    public AuthenticationResultDto getAuthenticationResult(AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userDao.findByLogin(username)
                    .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
            String token = jwtTokenProvider.createToken(username, user.getRole());
            return new AuthenticationResultDto(token, validityInMilliseconds);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
