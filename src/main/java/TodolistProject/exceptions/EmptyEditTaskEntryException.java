package TodolistProject.exceptions;

public class EmptyEditTaskEntryException extends RuntimeException {
    public EmptyEditTaskEntryException(String message) {
        super(message);
    }
}
