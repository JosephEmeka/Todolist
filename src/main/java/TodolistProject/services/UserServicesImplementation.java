package TodolistProject.services;


import TodolistProject.data.model.Task;
import TodolistProject.data.model.User;
import TodolistProject.data.repository.UserRepository;
import TodolistProject.dtos_requests.*;
import TodolistProject.dtos_response.*;
import TodolistProject.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import static TodolistProject.utils.Mapper.*;


@Service
public class UserServicesImplementation implements UserServices{
    @Autowired
    private TaskServices taskServices;
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
        Optional<User> existingUser = userRepository.findByUserName(loginRequest.getUsername());
        if (existingUser.isEmpty()) {
            throw new NoSuchElementException("User with username " + loginRequest.getUsername() + " not found");
        }

        User user = existingUser.get();
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new WrongPasswordException("Invalid password");
        }
        if (user.getIsLoggedIn()) {
            throw new UserAlreadyLoggedInException("User already logged in");
        }
        user.setIsLoggedIn(true);
        userRepository.save(user);
        return userLoginResponseMap(user);
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


    @Override
    public AddTaskResponse AddTaskToUserRepository(RegisterTaskRequest newTaskRequest) {
        return taskServices.addTask(newTaskRequest);
    }

    @Override
    public GetAllTaskResponse getAllTasksForUser(GetAllTaskRequest  getAllTaskRequest) {
        return taskServices.getAllTasks(getAllTaskRequest);
    }

}
