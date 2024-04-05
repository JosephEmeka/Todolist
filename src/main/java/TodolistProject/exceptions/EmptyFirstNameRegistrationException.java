package TodolistProject.exceptions;

public class EmptyFirstNameRegistrationException extends RuntimeException {
    public EmptyFirstNameRegistrationException(String message) {
        super(message);
    }
}
