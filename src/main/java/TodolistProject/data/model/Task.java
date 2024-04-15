package TodolistProject.data.model;

import TodolistProject.enums.PendingStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String taskId;
    private String title;
    private String description;
    private String author;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime dueDateTime;
    private PendingStatus status = PendingStatus.PENDING;
}
