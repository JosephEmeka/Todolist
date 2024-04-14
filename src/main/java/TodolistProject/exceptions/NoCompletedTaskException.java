package TodolistProject.exceptions;

public class NoCompletedTaskException extends RuntimeException {
    public NoCompletedTaskException(String message) {
        super(message);
    }
}
