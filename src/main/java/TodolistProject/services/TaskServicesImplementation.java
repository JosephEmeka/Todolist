package TodolistProject.services;

import TodolistProject.data.model.Task;
import TodolistProject.data.model.User;
import TodolistProject.data.repository.TaskRepository;
import TodolistProject.data.repository.UserRepository;
import TodolistProject.dtos_requests.*;
import TodolistProject.dtos_response.*;
import TodolistProject.enums.PendingStatus;
import TodolistProject.exceptions.*;
import TodolistProject.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static TodolistProject.utils.Mapper.*;

@Service
public class TaskServicesImplementation implements TaskServices {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServicesImplementation(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public AddTaskResponse addTask(RegisterTaskRequest newTaskRequest) {
        if (taskRepository.findByUsernameAndTitle(newTaskRequest.getUsername().toLowerCase().trim(), newTaskRequest.getTitle().toLowerCase().trim()).isEmpty()) {
            Optional<User> foundOptionalUser = userRepository.findByUserName(newTaskRequest.getUsername());

            if (foundOptionalUser.isPresent()) {
                User user = foundOptionalUser.get();
                if (user.getIsLoggedIn()) {
                    Task newTask = addTaskRequestMap(newTaskRequest);
                    newTask.setUsername(user.getUserName());

                    if (user.getTasks() == null) {
                        user.setTasks(new ArrayList<>());
                    }

                    user.getTasks().add(newTask);
                    taskRepository.save(newTask);
                    userRepository.save(user);

                    return addTaskResponseMap(newTask);
                } else {
                    throw new UserNotLoggedInException("Login is required to create task");
                }
            } else {
                throw new UserNotFoundException("User not found");
            }
        }
        throw new TaskAlreadyAddedException("Task already exists");
    }


    @Override
    public GetAllTaskResponse getAllTasks(GetAllTaskRequest getAllTaskRequest) {
        Optional<User> foundOptionalUser = userRepository.findByUserName(getAllTaskRequest.getUsername());
        if (foundOptionalUser.isPresent()) {
            User user = foundOptionalUser.get();
            if (user.getIsLoggedIn()) {
                return getAllTaskResponseMap(taskRepository.findByUsername(getAllTaskRequest.getUsername().toLowerCase().trim()).get());
            } else {
                throw new UserNotLoggedInException("User is not logged in");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }


    @Override
    public EditTaskResponse editTask(EditTaskRequest editTaskRequest) {
        Task existingTask = taskRepository.findByUsernameAndTitle(editTaskRequest.getUsername().toLowerCase().trim(), editTaskRequest.getTitle().toLowerCase().trim())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        Task updatedTask = editTaskRequestMap(editTaskRequest, existingTask);
        validateTaskRequest(updatedTask);

        taskRepository.save(updatedTask);

        return editTaskResponseMap(updatedTask);
    }


    public void validateTaskRequest(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty() || task.getAuthor() == null) {
            throw new EmptyEditTaskEntryException("Task details cannot be empty.");
        }
        if (task.getAuthor().equals(" ") || task.getTitle().equals(" ")) {
            throw new WhiteSpaceException("Book details cannot contain white space.");
        }
    }


    @Override
    public DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest) {
        Optional<User> userOptional = userRepository.findByUserName(deleteTaskRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getIsLoggedIn()) {
                String author = deleteTaskRequest.getAuthor().toLowerCase().trim();
                String title = deleteTaskRequest.getTitle().toLowerCase().trim();

                List<Task> tasks = user.getTasks();

                if (tasks != null) {
                    Optional<Task> taskToDelete = tasks.stream()
                            .filter(task -> task.getAuthor().equalsIgnoreCase(author) && task.getTitle().equalsIgnoreCase(title))
                            .findFirst();

                    if (taskToDelete.isPresent()) {
                        tasks.remove(taskToDelete.get());
                        if (taskRepository.findByUsername(taskToDelete.get().getUsername()).isPresent()) {
                            taskRepository.delete(taskToDelete.get());
                        }
                        userRepository.save(user);
                        return deleteTaskResponseMap(taskToDelete.get());
                    } else {
                        throw new NoSuchElementException("Task not found in user's list");
                    }
                } else {
                    throw new IllegalStateException("User's tasks list is null");
                }
            } else {
                throw new UserNotLoggedInException("Login is required to delete task");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public List<PendingTaskResponse> getPendingTasks(PendingTaskRequest pendingTaskRequest) {
        Optional<User> userOptional = userRepository.findByUserName(pendingTaskRequest.getUsername().toLowerCase().trim());
        List<PendingTaskResponse> pendingTaskResponses = new ArrayList<>();

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getIsLoggedIn()) {
                List<Task> tasks = user.getTasks();
                List<Task> pendingTasks = tasks.stream()
                        .filter(task -> task.getStatus().equals(pendingTaskRequest.getStatus()) && task.getAuthor().equalsIgnoreCase(pendingTaskRequest.getUsername()))
                        .collect(Collectors.toList());

                if (!pendingTasks.isEmpty()) {
                    pendingTaskResponses = pendingTasks.stream()
                            .map(Mapper::pendingTaskResponseMap)
                            .collect(Collectors.toList());
                } else {
                    throw new NoPendingTaskException("No Pending Task for this user");
                }
            } else {
                throw new UserNotLoggedInException("User is not logged in");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }

        return pendingTaskResponses;
    }

    @Override
    public CompletedTaskResponse markTaskAsCompleted(MarkTaskCompletedRequest markTaskCompletedRequest) {
        Optional<User> userOptional = userRepository.findByUserName(markTaskCompletedRequest.getUsername().toLowerCase().trim());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getIsLoggedIn()) {
                List<Task> tasks = user.getTasks();
                Optional<Task> taskToCompleteOptional = tasks.stream()
                        .filter(task -> task.getTitle().equalsIgnoreCase(markTaskCompletedRequest.getTitle()))
                        .findFirst();

                if (taskToCompleteOptional.isPresent()) {
                    Task taskToComplete = taskToCompleteOptional.get();

                    if (!taskToComplete.getStatus().equals(PendingStatus.COMPLETED)) {
                        taskToComplete.setStatus(PendingStatus.COMPLETED);
                        taskToComplete.setEndTime(LocalDateTime.now());

                        taskRepository.save(taskToComplete);
                        return completedTaskDurationResponseMap(taskToComplete);
                    } else {
                        throw new TaskAlreadyCompletedException("Task is already marked as completed.");
                    }
                } else {
                    throw new TaskNotFoundException("Task not found");
                }
            } else {
                throw new UserNotLoggedInException("User is not logged in");
            }

        } else throw new UserNotFoundException("User not found");
    }

    @Override
    public List<CompletedTaskResponse> getCompletedTasksWithDateTime(CompletedTaskRequest completedTaskRequest) {
        Optional<User> userOptional = userRepository.findByUserName(completedTaskRequest.getUsername().toLowerCase().trim());
        List<CompletedTaskResponse> completedTaskResponses = new ArrayList<>();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getIsLoggedIn()) {
                List<Task> tasks = user.getTasks();
                List<Task> completedTasks = tasks.stream()
                        .filter(task -> task.getStatus().equals(completedTaskRequest.getStatus()) && task.getAuthor().equalsIgnoreCase(completedTaskRequest.getUsername()))
                        .collect(Collectors.toList());

                if (!completedTasks.isEmpty()) {
                    completedTaskResponses = completedTasks.stream()
                            .map(Mapper::completedTaskDurationResponseMap)
                            .collect(Collectors.toList());
                } else {
                    throw new NoPendingTaskException("No Pending Task for this user");
                }
            } else {
                throw new UserNotLoggedInException("User is not logged in");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }

        return completedTaskResponses;
    }

    public SharedTaskResponse shareTask(ShareTaskRequest shareTaskRequest) {
        Task taskToShare = taskRepository.findByUsernameAndTitle(shareTaskRequest.getCurrentUsername(),shareTaskRequest.getTaskTitle())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        User recipientUser = userRepository.findByUserName(shareTaskRequest.getRecipientUsername())
                .orElseThrow(() -> new UserNotFoundException("Recipient user not found"));

        recipientUser.getTasks().add(taskToShare);
        userRepository.save(recipientUser);
        return sharedTaskResponseMap(recipientUser);

    }


    public AssignTaskResponse assignTask(AssignTaskRequest assignTaskRequest) {
        Task taskToAssign = taskRepository.findByUsernameAndTitle(assignTaskRequest.getCurrentUsername(), assignTaskRequest.getTaskTitle())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        User assigneeUser = userRepository.findByUserName(assignTaskRequest.getAssigneeUsername())
                .orElseThrow(() -> new UserNotFoundException("Assignee user not found"));

        assigneeUser.getTasks().add(taskToAssign);
        userRepository.save(assigneeUser);
        return assignTaskResponseMapper(assigneeUser);
    }


}
