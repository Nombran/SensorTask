package by.bsuir.sensor.user;

import by.bsuir.sensor.jwt.JwtUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/me")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public UserDto getUserByToken(Authentication authentication) {
        JwtUser user = (JwtUser)authentication.getPrincipal();
        return userService.find(user.getId());
    }
}
