package TodolistProject.dtos_requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterTaskRequest {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String username;
}
