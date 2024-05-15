package TodolistProject.Services;
import TodolistProject.data.model.Task;
import TodolistProject.data.repository.TaskRepository;
import TodolistProject.data.repository.UserRepository;
import TodolistProject.dtos_requests.RegisterTaskRequest;
import TodolistProject.services.TaskServicesImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaskServicesImplementationTest {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
}
//    @Test
//    void testThatUserCanAddTask() throws Exception {
//       taskRepository.deleteAll();
//       RegisterTaskRequest newTaskRequest = new RegisterTaskRequest();
//       newTaskRequest.setTitle("New Task");
//       newTaskRequest.setUsername("username");
//       newTaskRequest.setAuthor("joe");
//       newTaskRequest.setDescription("Semicolon Task");
//       LocalDateTime dueDateTime = LocalDateTime.of(2025, 1, 1, 12, 0);
//       newTaskRequest.setStartTime(LocalDateTime.now());
//       newTaskRequest.setDueDate(Timestamp.valueOf(dueDateTime).toLocalDateTime());
//       TaskServicesImplementation newTaskService = new TaskServicesImplementation(userRepository, taskRepository);
//       newTaskService.addTask(newTaskRequest);
//       assertEquals(1, taskRepository.count());
//    }
//
//    @Test
//    void UserCanViewListOfTask(){
//        taskRepository.deleteAll();
//        RegisterTaskRequest task1 = new RegisterTaskRequest();
//        task1.setTitle("Task 1");
//        task1.setDescription("Description for Task 1");
//        LocalDateTime dueDateTime1 = LocalDateTime.of(2023, 1, 1, 12, 0);
//        task1.setDueDate(Timestamp.valueOf(dueDateTime1).toLocalDateTime());
//
//        RegisterTaskRequest task2 = new RegisterTaskRequest();
//        task2.setTitle("Task 2");
//        task2.setDescription("Description for Task 2");
//        LocalDateTime dueDateTime2 = LocalDateTime.of(2023, 1, 2, 12, 0);
//        task2.setDueDate(Timestamp.valueOf(dueDateTime2).toLocalDateTime());
//
//        TaskServicesImplementation taskService = new TaskServicesImplementation(userRepository, taskRepository);
//        taskService.addTask(task1);
//        taskService.addTask(task2);
//
//        List<Task> tasks = taskService.getAllTasks();
//
//        assertEquals(2, tasks.size());
//        assertEquals("Task 1", tasks.get(0).getTitle());
//        assertEquals("Description for Task 2", tasks.get(1).getDescription());
//
//    }

//    @Test void testUserCanEditTask() {
//        taskRepository.deleteAll();
//
//        RegisterTaskRequest newTaskRequest = new RegisterTaskRequest();
//        newTaskRequest.setTitle("Task to Edit");
//        newTaskRequest.setDescription("Description for Task to Edit");
//        LocalDateTime dueDateTime = LocalDateTime.of(2023, 1, 3, 12, 0);
//        newTaskRequest.setDueDate(Timestamp.valueOf(dueDateTime).toLocalDateTime());
//
//        TaskServicesImplementation taskService = new TaskServicesImplementation(taskRepository);
//        taskService.addTask(newTaskRequest);
//
//        List<Task> tasks = taskService.getAllTasks();
//        Task TaskToBeEdited = tasks.get(0);
//
//
//        TaskToBeEdited.setTitle("Edited Task Title");
//        TaskToBeEdited.setDescription("Edited Task Description");
//
//        taskService.editTask(TaskToBeEdited);
//
//
//        Task editedTask = taskService.editTask(TaskToBeEdited.getTaskId());
//
//        assertEquals("Edited Task Title", editedTask.getTitle());
//        assertEquals("Edited Task Description", editedTask.getDescription());
//    }
//
//    @Test void testUserCanDeleteTask() { taskRepository.deleteAll();
//
//        RegisterTaskRequest task = new RegisterTaskRequest();
//        task.setTitle("Task to Delete");
//        task.setDescription("Description for Task to Delete");
//        LocalDateTime dueDateTime = LocalDateTime.of(2023, 1, 4, 12, 0);
//        task.setDueDate(Timestamp.valueOf(dueDateTime).toLocalDateTime());
//
//        TaskServicesImplementation taskService = new TaskServicesImplementation(taskRepository);
//        taskService.addTask(task);
//
//        List<Task> tasksBeforeDeletion = taskService.getAllTasks();
//
//        Task taskToDelete = tasksBeforeDeletion.get(0);
//
//        taskService.deleteTask(taskToDelete.getTaskId());
//
//        List<Task> tasksAfterDeletion = taskService.getAllTasks();
//
//        assertEquals(0, tasksAfterDeletion.size());
//    }
//
//}