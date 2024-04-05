package TodolistProject.utils;


import TodolistProject.data.model.User;
import TodolistProject.dtos_requests.LogOutRequest;
import TodolistProject.dtos_requests.LoginRequest;
import TodolistProject.dtos_requests.RegisterRequest;
import TodolistProject.dtos_response.LoginResponse;
import TodolistProject.dtos_response.RegisterResponse;
import TodolistProject.dtos_response.LogoutResponse;


public class Mapper {
    public static User RegisterRequestMap(RegisterRequest newUserRegistrationRequest) {
        User user = new User();
        user.setUserName(newUserRegistrationRequest.getUserName());
        user.setFirstName(newUserRegistrationRequest.getFirstName());
        user.setLastName(newUserRegistrationRequest.getLastName());
        user.setPassword(newUserRegistrationRequest.getPassword());
        user.setEmail(newUserRegistrationRequest.getEmail());
        return user;
    }

    public static RegisterResponse RegisterResponseMap(User user) {
        RegisterResponse response = new RegisterResponse();
        response.setId(user.getUserId());
        response.setUserName(user.getUserName());
        return response;
    }


    public static User userLoginMap(LoginRequest loginRequest){
            User user = new User();
            user.setUserName(loginRequest.getUsername());
            user.setPassword(loginRequest.getPassword());
            return user;
        }

        public static LoginResponse userLoginResponseMap(User user){
            LoginResponse userLoginResponse = new LoginResponse();
            userLoginResponse.setUserName(user.getUserName());
            userLoginResponse.setId(user.getUserId());
            return userLoginResponse;
        }

    public static User userLogOutMap(LogOutRequest logOutRequest){
        User user = new User();
        user.setUserName(logOutRequest.getUsername());
        user.setPassword(logOutRequest.getPassword());
        return user;
    }

    public static LogoutResponse userLogOutResponseMap(User user){
        LogoutResponse userLogoutResponse = new LogoutResponse();
        userLogoutResponse.setUserName(user.getUserName());
        userLogoutResponse.setId(user.getUserId());
        return  userLogoutResponse;
    }



}



