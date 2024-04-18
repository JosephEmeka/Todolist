package TodolistProject.controllers;


import TodolistProject.dtos_requests.*;
import TodolistProject.dtos_response.ApiResponse;
import TodolistProject.dtos_response.CompletedTaskResponse;
import TodolistProject.exceptions.TaskAlreadyAddedException;
import TodolistProject.exceptions.TaskAlreadyCompletedException;
import TodolistProject.exceptions.TaskNotFoundException;
import TodolistProject.services.TaskServices;
import TodolistProject.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/todolist")
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
    public ResponseEntity<?> getAllTasks() {
        try {
            var result = taskServices.getAllTasks();
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
            CompletedTaskResponse response = taskServices.markTaskAsCompleted(markTaskCompletedRequest);
            return ResponseEntity.ok(response);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(false, e.getMessage()));
        } catch (TaskAlreadyCompletedException e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(false, e.getMessage()));
        }
    }
}

