package TodolistProject.controllers;


import TodolistProject.dtos_requests.LogOutRequest;
import TodolistProject.dtos_requests.LoginRequest;
import TodolistProject.dtos_requests.RegisterRequest;
import TodolistProject.dtos_response.ApiResponse;
import TodolistProject.services.UserServicesImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;


public class ServicesControllers {
    @RestController
    @RequestMapping("/elibrary")
    public static class BlogServicesController {
        private final UserServicesImplementation userServices;


        public BlogServicesController(UserServicesImplementation userServices) {
            this.userServices = userServices;

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

    }
}
