package TodolistProject.exceptions;

public class TaskAlreadyAddedException extends Throwable {
    public TaskAlreadyAddedException(String taskAlreadyAdded) {
        super(taskAlreadyAdded);
    }
}
