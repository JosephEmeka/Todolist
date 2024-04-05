package TodolistProject.services;


import TodolistProject.dtos_requests.LoginRequest;
import TodolistProject.dtos_requests.RegisterRequest;
import TodolistProject.dtos_response.LoginResponse;
import TodolistProject.dtos_response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserServices{
    public RegisterResponse registerUser(RegisterRequest newUserRegistrationRequest);
    public LoginResponse loginUser(LoginRequest loginRequest);

}
