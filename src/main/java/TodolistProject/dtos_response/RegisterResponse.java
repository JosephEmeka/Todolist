package TodolistProject.dtos_response;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class RegisterResponse {
    private String userName;
    private String id;
    private LocalDateTime dateRegistered;
}
