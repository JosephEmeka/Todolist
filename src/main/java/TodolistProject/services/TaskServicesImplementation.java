package TodolistProject.services;

import TodolistProject.data.model.Task;
import TodolistProject.data.repository.TaskRepository;
import TodolistProject.dtos_requests.*;
import TodolistProject.dtos_response.*;
import TodolistProject.enums.PendingStatus;
import TodolistProject.exceptions.*;
import TodolistProject.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static TodolistProject.utils.Mapper.*;

@Service
public class TaskServicesImplementation implements TaskServices {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskServicesImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public AddTaskResponse addTask(RegisterTaskRequest newTaskRequest) {
        if(taskRepository.findByAuthorAndTitle(newTaskRequest.getAuthor(), newTaskRequest.getTitle()).isEmpty()) {
            Task newTask = addTaskRequestMap(newTaskRequest);
            taskRepository.save(newTask);
            return addTaskResponseMap(newTask);
        }
        throw new TaskAlreadyAddedException("Task already exists");
    }

    @Override
    public List<Task> getAllTasks() { return taskRepository.findAll(); }

    @Override
    public EditTaskResponse editTask(EditTaskRequest editTaskRequest)  {
        Task existingTask = taskRepository.findByAuthorAndTitle(editTaskRequest.getAuthor().toLowerCase().trim(), editTaskRequest.getTitle().toLowerCase().trim())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        Task updatedTask = editTaskRequestMap(editTaskRequest, existingTask);
        validateTaskRequest(updatedTask);

        taskRepository.save(updatedTask);

        return editTaskResponseMap(updatedTask);
    }


public void  validateTaskRequest(Task task) {
    if (task.getTitle() == null || task.getTitle().isEmpty() || task.getAuthor() == null) {
        throw new EmptyEditTaskEntryException("Task details cannot be empty.");
    }
    if (task.getAuthor().equals(" ") || task.getTitle().equals(" ")) {
        throw new WhiteSpaceException("Book details cannot contain white space.");
    }
}


    @Override
    public DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest) {
        Task taskToDelete = deleteTaskRequestMap(deleteTaskRequest);
        if(taskRepository.findByAuthorAndTitle(deleteTaskRequest.getAuthor(), deleteTaskRequest.getTitle()).isPresent()) {
            String foundTaskToBeDeletedId = taskRepository.findByAuthorAndTitle(deleteTaskRequest.getAuthor(), deleteTaskRequest.getTitle()).get().getTaskId();
            taskRepository.deleteById(foundTaskToBeDeletedId);
            return deleteTaskResponseMap(taskToDelete);
        }

        throw new NoSuchElementException("task does not exist in database");
    }

@Override
    public List<PendingTaskResponse> getPendingTasks(PendingTaskRequest pendingTaskRequest) {
        Optional<List<Task>> pendingTasks = taskRepository.findByStatusAndAuthor(pendingTaskRequest.getStatus(), pendingTaskRequest.getAuthor().toLowerCase().trim());
        if (pendingTasks.isPresent()) {
            return pendingTasks.get().stream()
                    .map(Mapper::pendingTaskResponseMap)
                    .collect(Collectors.toList());
        }
        throw new NoPendingTaskException("No Pending Task for this " + pendingTaskRequest.getAuthor());
    }
@Override
    public CompletedTaskResponse markTaskAsCompleted(MarkTaskCompletedRequest markTaskCompletedRequest) {

        Task taskToComplete = taskRepository.findByAuthorAndTitle(markTaskCompletedRequest.getAuthor().toLowerCase().trim(), markTaskCompletedRequest.getTitle().toLowerCase().trim())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!taskToComplete.getStatus().equals(PendingStatus.COMPLETED)) {
            taskToComplete.setStatus(PendingStatus.COMPLETED);
            taskToComplete.setEndTime(LocalDateTime.now());

            Duration completionTime = Duration.between(taskToComplete.getStartTime(), taskToComplete.getEndTime());

            taskRepository.save(taskToComplete);

            return completedTaskDurationResponseMap(taskToComplete, completionTime);
        } else {
            throw new TaskAlreadyCompletedException("Task is already marked as completed.");
        }
    }

@Override
    public List<CompletedTaskResponse> getCompletedTasksWithDateTime(CompletedTaskRequest completedTaskRequest) {
        Optional<List<Task>> completedTasks = taskRepository.findByStatusAndAuthor(completedTaskRequest.getStatus(), completedTaskRequest.getAuthor().toLowerCase().trim());

        if (completedTasks.isPresent()) {
            return completedTasks.get().stream()
                    .map(Mapper::completedTaskResponseMap)
                    .collect(Collectors.toList());
        }

        throw new NoCompletedTaskException("No Completed Tasks found.");
    }

}


