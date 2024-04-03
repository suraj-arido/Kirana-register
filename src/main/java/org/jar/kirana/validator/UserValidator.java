package org.jar.kirana.validator;

import org.jar.kirana.dto.UserDto;
import org.jar.kirana.model.objects.UserModel;
import org.jar.kirana.resository.UserRepository;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean isAlphaNumeric(String userName) {
        String regex = "[a-zA-Z]+[a-zA-Z0-9_]*";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(userName).matches();
    }

    private boolean emailIsvalid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    /*
     * This Return True if User with the given email exists
     * Return False if user doesn't exist
     */
    private boolean userExistsInDatabase(String email) {
        UserModel user = userRepository.findOneByEmail(email);
        return user != null;
    }

    private boolean allRequiredFieldsExits(UserDto userDto) {
        if (userDto.getUserPassword() == null) {
            return false;
        }
        if (userDto.getEmail() == null) {
            return false;
        }
        if (userDto.getUsername() == null) {
            return false;
        }
        if (userDto.getFirstName() == null) {
            return false;
        }
        return userDto.getLastName() != null;
    }

    public void validate(UserDto userDto) {

        if (!isAlphaNumeric(userDto.getUsername())) {
            throw new InvalidParameterException("Invalid UserName, UserName must start with" +
                    " alphabet and can contain only \"_\" and alphanumeric values");
        }
        if (!emailIsvalid(userDto.getEmail())) {
            throw new InvalidParameterException("Entre A valid Email ID");
        }
        if (!allRequiredFieldsExits(userDto)) {
            throw new InvalidParameterException("All required fields are not present");
        }
        if (userExistsInDatabase(userDto.getEmail())) {
            throw new InvalidParameterException("User Already Exists, Kindly login");
        }
    }
}
