package org.jar.kirana.service;

//System.out.println(MessageFormat.format("error {0}",e));

import jakarta.ws.rs.core.Response;
import org.jar.kirana.dto.UserDto;
import org.jar.kirana.mapper.UserMapper;
import org.jar.kirana.model.objects.UserModel;
import org.jar.kirana.model.responses.ApiResponse;
import org.jar.kirana.resository.UserRepository;
import org.jar.kirana.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;
    private final KeycloakService keycloakService;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, UserValidator userValidator, KeycloakService keycloakService){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidator = userValidator;
        this.keycloakService = keycloakService;
    }

    public ApiResponse createUser(UserDto userDto){
        ApiResponse response = new ApiResponse();
        try{
            userValidator.validate(userDto);
            Response response1 = keycloakService.createKeycloakUser(userDto);
            String userId = response1.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            UserModel user = userMapper.toUser(userDto);
            user.setUserId(userId);
            UserModel savedUser = userRepository.save(user);
            UserDto savedUserDto = userMapper.toDto(savedUser);
            response.setData(savedUserDto);
            response.setDisplayMsg("User Created Successfully");
            response.setStatus("201");
        }
        catch (Exception e){
            response.setSuccess(false);
            response.setStatus("409");
            response.setData(userDto);
            response.setErrorMessage(e.getMessage());
            response.setDisplayMsg(e.getLocalizedMessage());
        }
        return response;
    }
    public String testRequestData(UserDto userDto){
        UserModel user = userMapper.toUser(userDto);
        return user.toString();
    }
}