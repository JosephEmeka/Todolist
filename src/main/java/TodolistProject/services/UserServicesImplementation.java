package TodolistProject.services;


import TodolistProject.data.model.User;
import TodolistProject.data.repository.UserRepository;
import TodolistProject.dtos_requests.LogOutRequest;
import TodolistProject.dtos_requests.LoginRequest;
import TodolistProject.dtos_requests.RegisterRequest;
import TodolistProject.dtos_response.LoginResponse;
import TodolistProject.dtos_response.LogoutResponse;
import TodolistProject.dtos_response.RegisterResponse;
import TodolistProject.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import static TodolistProject.utils.Mapper.*;


@Service
public class UserServicesImplementation implements UserServices{
    private final UserRepository userRepository;
    @Autowired
    public UserServicesImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public RegisterResponse registerUser(RegisterRequest newUserRegistrationRequest) {
        User user = RegisterRequestMap(newUserRegistrationRequest);
        validateUser(user);
        userRepository.save(user);
        return RegisterResponseMap(user);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
//        User user = u(loginRequest);
        Optional<User> existingUser = userRepository.findByUserName(loginRequest.getUsername());
        if (existingUser.isEmpty()) {
            throw new DoubleUserRegistrationException("User with username " + loginRequest.getUsername() + " not found");
        }
//        validateLoginRequest(loginRequest, user);
        User user = existingUser.get();
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new WrongPasswordException("Invalid password");
        }
        user.setIsLoggedIn(true);
        userRepository.save(user);
        return userLoginResponseMap(user);
    }

    private void validateLoginRequest(LoginRequest loginRequest, User user) {
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty()) {
            throw new EmptyUserNameLoginException("User name cannot be empty.");
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new EmptyPasswordLoginException("Password cannot be empty.");
        }
        if (loginRequest.getUsername().equals(" ") || loginRequest.getPassword().equals(" ")) {
            throw new WhiteSpaceException("User cannot enter white Space");
        }
        Optional<User> existingUserOptional = userRepository.findByUserName(user.getUserName());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            if (!Objects.equals(existingUser.getPassword(), loginRequest.getPassword())) {
                throw new WrongPasswordException("Account does not exist or Invalid password");
            }
            existingUser.setIsLoggedIn(true);
            userRepository.save(existingUser);
        }
        else
        {
            throw new NoSuchElementException("User not found");
        }


    }

    private void validateUser(User user)  {
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new EmptyUserNameRegistrationException("User name cannot be empty.");
        }
        if (user.getUserName().equals(" ") || user.getFirstName().equals(" ") || user.getLastName().equals(" ") || user.getPassword().equals(" ")) {
            throw new WhiteSpaceException("User cannot enter white Space");
        }
        if (user.getFirstName().isEmpty())
            throw new EmptyFirstNameRegistrationException("First name cannot be empty.");
        if(user.getLastName().isEmpty()) {
            throw new EmptyLastNameRegistrationException("Last name cannot be empty.");
        }

        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new DoubleUserRegistrationException("User with username " + user.getUserName() + " already exists.");
        }
    }

    @Override
    public LogoutResponse logoutUser(LogOutRequest newLogOutRequest) {
        User user = userLogOutMap(newLogOutRequest);
        validateLogoutRequest(newLogOutRequest, user);
        return userLogOutResponseMap(user);
    }


    private void validateLogoutRequest(LogOutRequest logOutRequest, User user) {
        Optional<User> existingUserOptional = userRepository.findByUserName(user.getUserName());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            if (!Objects.equals(existingUser.getPassword(), logOutRequest.getPassword())) {
                throw new WrongPasswordException("Account does not exist or Invalid password");
            }
            existingUser.setIsLoggedIn(false);
            userRepository.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found");
        }

    }

}
