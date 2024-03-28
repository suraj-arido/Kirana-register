package org.jar.kirana.service;

//System.out.println(MessageFormat.format("error {0}",e));

import org.jar.kirana.dto.UserDto;
import org.jar.kirana.mapper.UserMapper;
import org.jar.kirana.model.objects.UserModel;
import org.jar.kirana.model.responses.UserApiResponse;
import org.jar.kirana.resository.UserRepository;
import org.jar.kirana.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator validator;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, UserValidator validator){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.validator = validator;
    }

    public UserApiResponse createUser(UserDto userDto){
        try{
            validator.validate(userDto);
            UserModel user = userMapper.toUser(userDto);
            UserModel savedUser = userRepository.save(user);
            UserDto savedUserDto = userMapper.toDto(savedUser);
            UserApiResponse response = new UserApiResponse();
            response.setData(savedUserDto);
            response.setDisplayMsg("User Created Successfully");
            response.setStatus("201");
            return response;
        }
        catch (Exception e){
            UserApiResponse failedResponse = new UserApiResponse();
            failedResponse.setSuccess(false);
            failedResponse.setStatus("409");
            failedResponse.setData(userDto);
            failedResponse.setErrorMessage(e.getMessage());
            failedResponse.setDisplayMsg(e.getLocalizedMessage());
            return failedResponse;
        }
    }
    public String testRequestData(UserDto userDto){
        UserModel user = userMapper.toUser(userDto);
        return user.toString();
    }
}