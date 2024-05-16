package TodolistProject.dtos_requests;

import lombok.Data;

@Data
public class MarkTaskCompletedRequest {
    private String title;
    private String username;
}
