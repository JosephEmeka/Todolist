package TodolistProject.controllers;


import TodolistProject.dtos_requests.*;
import TodolistProject.dtos_response.ApiResponse;
import TodolistProject.services.TaskServices;
import TodolistProject.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/todolist")
@CrossOrigin(origins = "*")
public class ServicesControllers {
    @Autowired
    private UserServices userServices;
    @Autowired
    private TaskServices taskServices;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            var result = userServices.registerUser(registerRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }

    @PatchMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            var result = userServices.loginUser(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogOutRequest logOutRequest) {
        try {
            var result = userServices.logoutUser(logOutRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }

    }

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody RegisterTaskRequest newTaskRequest) {
        try {
            var result = taskServices.addTask(newTaskRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<?> getAllTasks(@RequestBody GetAllTaskRequest getAllTaskRequest) {
        try {
            var result = taskServices.getAllTasks(getAllTaskRequest);
            return new ResponseEntity<>(result, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }


    @PatchMapping("/editTask")
    public ResponseEntity<?> editTask(@RequestBody EditTaskRequest editTaskRequest) {
        try {
            var result = taskServices.editTask(editTaskRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<?> deleteTask(@RequestBody DeleteTaskRequest deleteTaskRequest) {
        try {
            var result = taskServices.deleteTask(deleteTaskRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/getPendingTasks")
    public ResponseEntity<?> getPendingTasks(@RequestBody PendingTaskRequest pendingTaskRequest) {
        try {
            var result = taskServices.getPendingTasks(pendingTaskRequest);
            return new ResponseEntity<>(result, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/getCompletedTasksWithDateTime")
    public ResponseEntity<?> getCompletedTasksWithDateTime(@RequestBody CompletedTaskRequest completedTaskRequest) {
        try {
            var result = taskServices.getCompletedTasksWithDateTime(completedTaskRequest);
            return new ResponseEntity<>(result, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/markTaskAsCompleted")
    public ResponseEntity<?> markTaskAsCompleted(@RequestBody MarkTaskCompletedRequest markTaskCompletedRequest) {
        try {
            var result  = taskServices.markTaskAsCompleted(markTaskCompletedRequest);
            return new ResponseEntity<>(result, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}

