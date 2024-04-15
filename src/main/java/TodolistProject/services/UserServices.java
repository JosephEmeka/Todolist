package TodolistProject.services;


import TodolistProject.dtos_requests.LogOutRequest;
import TodolistProject.dtos_requests.LoginRequest;
import TodolistProject.dtos_requests.RegisterRequest;
import TodolistProject.dtos_response.LoginResponse;
import TodolistProject.dtos_response.LogoutResponse;
import TodolistProject.dtos_response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserServices{
     RegisterResponse registerUser(RegisterRequest newUserRegistrationRequest);
     LoginResponse loginUser(LoginRequest loginRequest);
    LogoutResponse logoutUser(LogOutRequest newLogOutRequest);

}
