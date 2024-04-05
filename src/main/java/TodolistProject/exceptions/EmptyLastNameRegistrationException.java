package TodolistProject.exceptions;

public class EmptyLastNameRegistrationException extends RuntimeException {
    public EmptyLastNameRegistrationException(String message) {
        super(message);
    }
}
