package by.bsuir.sensor.user;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    public UserDto find(long id) {
        User user = userDao.find(id).orElseThrow(
                () -> new UserNotFoundException("There is no user with id " + id)
        );
        return modelMapper.map(user, UserDto.class);
    }
}
