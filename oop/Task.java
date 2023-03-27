package edu.ucalgary.oop;

public class Task {
    private final int TASKID;
    private int DURATION;
    private int timeWindow;
    private String description;

    public Task(int taskID, int duration, int timeWindow, String description) {
        this.TASKID = taskID;
        this.DURATION = duration;
        this.timeWindow = timeWindow;
        this.description = description;
    }

    public int getID() {
        return this.TASKID;
    }

    public int getDURATION() {
        return this.DURATION;
    }

    public int getTimeWindow() {
        return this.timeWindow;
    }

    public String getDescription() {
        return this.description;
    }
}