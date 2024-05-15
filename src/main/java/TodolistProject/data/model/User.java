package TodolistProject.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private Boolean isLoggedIn = false;
    private LocalDateTime dateRegistered;
    private List<Task> tasks;
}
