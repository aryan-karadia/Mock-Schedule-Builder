package edu.ucalgary.oop;

public class Task {
    private final int TASKID;
    private final int DURATION;
    private final int timeWindow;
    private final String description;

    public Task(int taskID, int duration, int timeWindow, String description) {
        // todo - validate input
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