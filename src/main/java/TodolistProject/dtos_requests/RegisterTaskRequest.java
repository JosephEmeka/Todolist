package TodolistProject.dtos_requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterTaskRequest {
    private String title;
    private String description;
    private String author;
    private LocalDateTime startTime;
    private LocalDateTime dueDate;
    private String username;
}
