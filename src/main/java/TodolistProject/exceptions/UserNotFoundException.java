package TodolistProject.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
