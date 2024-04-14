package TodolistProject.dtos_response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditTaskResponse {
        private String taskId;
        private String title;
        private String description;
        private String status;
        private LocalDateTime startTime;
        private LocalDateTime dueDate;
    }

