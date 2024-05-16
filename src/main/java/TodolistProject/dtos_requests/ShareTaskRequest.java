package TodolistProject.dtos_requests;

import lombok.Data;

@Data
public class ShareTaskRequest {
    private String currentUsername;
    private String taskTitle;
    private String recipientUsername;
    private String email;
}
