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

    public UserDto create(UserDto userDto) {
        userDto.setId(0);
        User user = modelMapper.map(userDto, User.class);
        userDao.create(user);
        return modelMapper.map(user, UserDto.class);
    }


}
