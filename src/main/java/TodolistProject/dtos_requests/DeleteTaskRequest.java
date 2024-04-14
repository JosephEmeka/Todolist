package TodolistProject.dtos_requests;

import lombok.Data;

@Data
public class DeleteTaskRequest {
    private String title;
    private String author;
}

