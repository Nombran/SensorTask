package by.bsuir.sensor.user;

import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class UserDtoMapper {
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setPassword)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserDto.class, User.class).setPostConverter(toEntityConverter());
    }

    public Converter<User, UserDto> toDtoConverter() {
        return context -> {
            UserDto destination = context.getDestination();
            mapSpecificFields(destination);
            return context.getDestination();
        };
    }

    public Converter<UserDto, User> toEntityConverter() {
        return context -> {
            UserDto source = context.getSource();
            User destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(UserDto source, User destination) {
        String password = source.getPassword();
        if(password != null) {
            destination.setPassword(passwordEncoder.encode(password));
        }
        destination.setRole(UserRole.ROLE_USER);
    }

    public void mapSpecificFields(UserDto destination) {
        destination.setPassword(null);
    }
}
