package TodolistProject.dtos_requests;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class EditTaskRequest {
        private String taskId;
        private String title;
        private String description;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private LocalDateTime dueDate;

    }


