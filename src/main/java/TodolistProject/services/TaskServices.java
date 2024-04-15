package TodolistProject.services;

import TodolistProject.data.model.Task;
import TodolistProject.dtos_requests.*;
import TodolistProject.dtos_response.*;
import TodolistProject.exceptions.TaskAlreadyAddedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskServices {
     AddTaskResponse addTask(RegisterTaskRequest newTaskRequest);
    List<Task> getAllTasks();

    EditTaskResponse editTask(EditTaskRequest editTaskRequest) throws TaskAlreadyAddedException;

    DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest);
    List<PendingTaskResponse> getPendingTasks(PendingTaskRequest pendingTaskRequest);
    CompletedTaskResponse markTaskAsCompleted(MarkTaskCompletedRequest markTaskCompletedRequest);
    List<CompletedTaskResponse> getCompletedTasksWithDateTime(CompletedTaskRequest completedTaskRequest);
}
