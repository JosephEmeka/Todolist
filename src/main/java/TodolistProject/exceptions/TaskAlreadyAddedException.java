package TodolistProject.exceptions;

public class TaskAlreadyAddedException extends RuntimeException {
    public TaskAlreadyAddedException(String taskAlreadyAdded) {
        super(taskAlreadyAdded);
    }
}
