package TodolistProject.services;

import TodolistProject.data.model.Task;
import TodolistProject.dtos_requests.DeleteTaskRequest;
import TodolistProject.dtos_requests.EditTaskRequest;
import TodolistProject.dtos_requests.RegisterTaskRequest;
import TodolistProject.dtos_response.AddTaskResponse;
import TodolistProject.dtos_response.DeleteTaskResponse;
import TodolistProject.dtos_response.EditTaskResponse;
import TodolistProject.exceptions.TaskAlreadyAddedException;
import org.springframework.stereotype.Service;

@Service
public interface TaskServices {
     AddTaskResponse addTask(RegisterTaskRequest newTaskRequest);

    EditTaskResponse editTask(EditTaskRequest editTaskRequest) throws TaskAlreadyAddedException;

    DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest);

}
