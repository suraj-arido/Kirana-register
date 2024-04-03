package org.jar.kirana.dto;

import lombok.Data;
@Data
public class UserDto {
    private String username;
    private String email;
    private String lastName;
    private String firstName;
    private String userPassword;
}