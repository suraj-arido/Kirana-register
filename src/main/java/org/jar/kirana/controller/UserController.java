package org.jar.kirana.controller;

import org.jar.kirana.dto.UserDto;
import org.jar.kirana.model.responses.ApiResponse;
import org.jar.kirana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/newUser")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDto userDto){
        ApiResponse createdUserResponse = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserResponse, HttpStatus.CREATED);
    }
    @PostMapping("/test")
    public ResponseEntity<String> test(){
//        System.out.println("somethign");
        return new ResponseEntity<>("The end point was hit", HttpStatus.OK);
    }
    @GetMapping("/greetings")
    public ResponseEntity<String> greetings(){
        String message = "hello this end points works";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PostMapping("/testing")
    public ResponseEntity<String> checking(@RequestBody UserDto userDto){
        String message = userService.testRequestData(userDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}