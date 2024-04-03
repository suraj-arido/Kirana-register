package org.jar.kirana.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

//@RestController
//@RequestMapping("/jwt")
//public class GeneralController {
//    @GetMapping("/hello")
//    public String greeting(){
//        return "Welcome, goAhead and give perfect routes after authentication and perform your operations";
//    }
//    @GetMapping("/admin")
//    public String greetingAdmin(){
//        return "Amin Reached";
//    }
//    @GetMapping("/user")
//    public String greetingUser(){
//        return "User Reached";
//    }
//}

@RestController
@RequestMapping("/test")
public class GeneralController{

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello Api is running");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> sayHelloToAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/user")
    public ResponseEntity<String> sayHelloToUser() {
        return ResponseEntity.ok("Hello User");
    }
}