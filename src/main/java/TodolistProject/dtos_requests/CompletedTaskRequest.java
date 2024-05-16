package TodolistProject.dtos_requests;

import TodolistProject.enums.PendingStatus;
import lombok.Data;

@Data
public class CompletedTaskRequest {
    private PendingStatus status = PendingStatus.COMPLETED;
    private String author;
    private String username;
}
