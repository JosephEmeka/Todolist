package TodolistProject.dtos_response;

import lombok.Data;

import java.util.List;

@Data
public class PendingTaskResponse {
    private String taskId;
    private String title;
    private String description;

}

