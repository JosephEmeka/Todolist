package TodolistProject.exceptions;

public class EmptyPasswordLoginException extends RuntimeException {
    public EmptyPasswordLoginException(String message) {
        super(message);
    }
}
