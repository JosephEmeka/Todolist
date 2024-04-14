package TodolistProject.data.repository;

import TodolistProject.data.model.Task;
import TodolistProject.enums.PendingStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByStatus(PendingStatus status);

    Task findByAuthor(String author);

     Optional<Task> findByAuthorAndTitle(String author, String author1);

    Optional<List<Task>> findByStatusAndAuthor(PendingStatus status, String author);
}
