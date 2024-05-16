package TodolistProject.dtos_requests;

import lombok.Data;

@Data
public class AssignTaskRequest {
    private String currentUsername;
    private String taskTitle;
    private String assigneeUsername;
    private String email;
}
