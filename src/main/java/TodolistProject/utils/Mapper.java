package TodolistProject.utils;


import TodolistProject.data.model.Task;
import TodolistProject.data.model.User;
import TodolistProject.dtos_requests.*;
import TodolistProject.dtos_response.*;


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

    public  static Task addTaskRequestMap(RegisterTaskRequest newTaskRequest) {
        Task task = new Task();
        task.setTitle(newTaskRequest.getTitle());
        task.setDescription(newTaskRequest.getDescription());
        task.setDueDateTime(newTaskRequest.getDueDate());
        return task;
    }

    public static AddTaskResponse addTaskResponseMap(Task task){
        AddTaskResponse addTaskResponse = new AddTaskResponse();
        addTaskResponse.setTitle(task.getTitle());
        addTaskResponse.setDescription(task.getDescription());
        addTaskResponse.setDueDate(task.getDueDateTime());
        return addTaskResponse;
    }

    public static Task editTaskRequestMap(EditTaskRequest request) {
        Task task = new Task();
            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setDueDateTime(request.getDueDate());
            task.setStartTime(request.getStartTime());
            task.setEndTime(request.getEndTime());
        return task;
    }

    public static EditTaskResponse editTaskResponseMap(Task task) {
        EditTaskResponse response = new EditTaskResponse();
        response.setTaskId(task.getTaskId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setDueDate(task.getDueDateTime());
        return response;
    }

    public static Task deleteTaskRequestMap(DeleteTaskRequest deleteTaskRequest){
        Task task = new Task();
            task.setAuthor(deleteTaskRequest.getAuthor());
            task.setTitle(deleteTaskRequest.getTitle());
        return task;
    }

    public static DeleteTaskResponse deleteTaskResponseMap(Task task){


        return null;
    }

    public static PendingTaskResponse pendingTaskResponseMap(Task task) {
        PendingTaskResponse response = new PendingTaskResponse();
        response.setTaskId(task.getTaskId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        return response;
    }

    public static CompletedTaskResponse completedTaskResponseMap(Task task) {
        CompletedTaskResponse response = new CompletedTaskResponse();
        response.setTaskId(task.getTaskId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        return response;
    }
}



