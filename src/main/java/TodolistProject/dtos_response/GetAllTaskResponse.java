package TodolistProject.dtos_response;

import TodolistProject.data.model.Task;
import lombok.Data;

import java.util.List;
@Data
public class GetAllTaskResponse {
    public String username;
    public List<Task> tasks;
}
