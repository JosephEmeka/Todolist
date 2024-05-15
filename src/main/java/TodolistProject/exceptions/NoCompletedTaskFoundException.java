package TodolistProject.exceptions;

public class NoCompletedTaskFoundException extends RuntimeException {
    public NoCompletedTaskFoundException(String message) {
        super(message);
    }
}
