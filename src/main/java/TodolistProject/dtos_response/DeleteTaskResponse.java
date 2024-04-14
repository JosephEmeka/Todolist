package TodolistProject.dtos_response;

import lombok.Data;

@Data
public class DeleteTaskResponse {
    private String taskId;
    private String title;
    private String author;
}
