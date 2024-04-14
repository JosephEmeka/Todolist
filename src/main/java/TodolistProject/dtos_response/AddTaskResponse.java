package TodolistProject.dtos_response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddTaskResponse {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime dueDate;
}
