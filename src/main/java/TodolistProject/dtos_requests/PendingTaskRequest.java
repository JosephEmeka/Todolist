package TodolistProject.dtos_requests;

import TodolistProject.enums.PendingStatus;
import lombok.Data;

@Data
public class PendingTaskRequest {
    private PendingStatus status = PendingStatus.PENDING;
    private String author;
   private String username;
}
