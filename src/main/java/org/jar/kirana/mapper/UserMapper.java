package org.jar.kirana.mapper;

import org.jar.kirana.dto.UserDto;
import org.jar.kirana.model.objects.UserModel;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public UserDto toDto(UserModel user){
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    public UserModel toUser(UserDto userDto){
        UserModel user = new UserModel();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
