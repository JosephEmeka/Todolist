package TodolistProject.exceptions;

public class NoPendingTaskException extends RuntimeException {
    public NoPendingTaskException(String message) {
        super(message);
    }
}
