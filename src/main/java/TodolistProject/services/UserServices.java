package TodolistProject.services;


import TodolistProject.data.model.Task;
import TodolistProject.dtos_requests.*;
import TodolistProject.dtos_response.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices{
     RegisterResponse registerUser(RegisterRequest newUserRegistrationRequest);
     LoginResponse loginUser(LoginRequest loginRequest);
     LogoutResponse logoutUser(LogOutRequest newLogOutRequest);
    AddTaskResponse AddTaskToUserRepository(RegisterTaskRequest newTaskRequest);
   GetAllTaskResponse getAllTasksForUser(GetAllTaskRequest newGetAllTaskRequest);

}
