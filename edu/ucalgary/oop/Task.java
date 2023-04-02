package edu.ucalgary.oop;

abstract class Task {
    protected final int TASKID;
    protected final int DURATION;
    protected final int timeWindow;
    protected final String description;

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