package TodolistProject.exceptions;

public class DoubleUserRegistrationException extends RuntimeException {
    public DoubleUserRegistrationException(String message) {
        super(message);
    }
}
