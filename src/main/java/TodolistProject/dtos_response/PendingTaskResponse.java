package TodolistProject.dtos_response;

import lombok.Data;

@Data
public class PendingTaskResponse {
    private String taskId;
    private String title;
    private String description;
}

