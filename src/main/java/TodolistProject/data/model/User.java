package TodolistProject.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


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
}
