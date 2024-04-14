package TodolistProject.dtos_requests;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class EditTaskRequest {
        private String author;
        private String title;
        private String description;
        private LocalDateTime startTime;
        private LocalDateTime dueDate;

    }


