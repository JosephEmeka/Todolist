package TodolistProject.exceptions;

public class UserAlreadyLoggedInException extends RuntimeException {
    public UserAlreadyLoggedInException(String userAlreadyLoggedIn) {
        super(userAlreadyLoggedIn);
    }
}
