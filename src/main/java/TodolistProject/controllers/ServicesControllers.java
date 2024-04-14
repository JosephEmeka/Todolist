package TodolistProject.controllers;


import TodolistProject.dtos_requests.*;
import TodolistProject.dtos_response.ApiResponse;
import TodolistProject.exceptions.TaskAlreadyAddedException;
import TodolistProject.services.TaskServicesImplementation;
import TodolistProject.services.UserServicesImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;


public class ServicesControllers {
    @RestController
    @RequestMapping("/todolist")
    public static class BlogServicesController {
        private final UserServicesImplementation userServices;
        private final TaskServicesImplementation taskServicesImplementation;

        @Autowired
        public BlogServicesController(UserServicesImplementation userServices, TaskServicesImplementation taskServicesImplementation) {
            this.userServices = userServices;
            this.taskServicesImplementation = taskServicesImplementation;
        }


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
                return new ResponseEntity<>(new ApiResponse(true, result), CONTINUE);
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
                var result = taskServicesImplementation.addTask(newTaskRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

        @GetMapping("/getAllTasks")
        public ResponseEntity<?> getAllTasks() {
            try {
                var result = taskServicesImplementation.getAllTasks();
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }

        @PatchMapping("/editTask")
        public ResponseEntity<?> editTask(@RequestBody EditTaskRequest editTaskRequest) {
            try {
                var result = taskServicesImplementation.editTask(editTaskRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            } catch (TaskAlreadyAddedException e) {
                throw new RuntimeException(e);
            }
        }

        @DeleteMapping("/deleteTask")
        public ResponseEntity<?> deleteTask(@RequestBody DeleteTaskRequest deleteTaskRequest) {
            try {
                var result = taskServicesImplementation.deleteTask(deleteTaskRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

        @PostMapping("/getPendingTasks")
        public ResponseEntity<?> getPendingTasks(@RequestBody PendingTaskRequest pendingTaskRequest) {
            try {
                var result = taskServicesImplementation.getPendingTasks(pendingTaskRequest);
                return new ResponseEntity<>(result, OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

        @PostMapping("/getCompletedTasksWithDateTime")
        public ResponseEntity<?> getCompletedTasksWithDateTime(@RequestBody CompletedTaskRequest completedTaskRequest) {
            try {
                var result =taskServicesImplementation.getCompletedTasksWithDateTime(completedTaskRequest);
                return new ResponseEntity<>(result, OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }
    }
}
