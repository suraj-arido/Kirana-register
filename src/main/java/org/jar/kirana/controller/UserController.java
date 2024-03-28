package org.jar.kirana.controller;

import org.jar.kirana.dto.UserDto;
import org.jar.kirana.model.responses.UserApiResponse;
import org.jar.kirana.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/new user")
    public ResponseEntity<UserApiResponse> createUser(@RequestBody UserDto userDto){
        UserApiResponse createdUserResponse = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserResponse, HttpStatus.CREATED);
    }
    @GetMapping("/greetings")
    public ResponseEntity<String> greetings(){
        String message = "hello this end points works";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PostMapping("testing")
    public ResponseEntity<String> checking(@RequestBody UserDto userDto){
        String message = userService.testRequestData(userDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}