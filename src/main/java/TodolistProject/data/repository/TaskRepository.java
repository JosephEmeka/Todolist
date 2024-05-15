package TodolistProject.data.repository;

import TodolistProject.data.model.Task;
import TodolistProject.enums.PendingStatus;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {

    Optional<List<Task>> findByUsername(String username);
     Optional<Task> findByUsernameAndTitle(String username, String title);

    Optional<List<Task>> findByStatusAndAuthor(PendingStatus status, String author);


}
