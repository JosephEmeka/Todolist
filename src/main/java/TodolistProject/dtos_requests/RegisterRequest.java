package TodolistProject.dtos_requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private LocalDateTime dateRegistered = LocalDateTime.now();

}
