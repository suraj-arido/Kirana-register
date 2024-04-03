package org.jar.kirana.mapper;

import org.jar.kirana.dto.UserDto;
import org.jar.kirana.model.objects.UserModel;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public UserDto toDto(UserModel user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        return userDto;
    }
    public UserModel toUser(UserDto userDto){
        UserModel user = new UserModel();
        user.setUsername(userDto.getUsername());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
