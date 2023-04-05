package edu.ucalgary.oop;

public class Treatment {
    private final int TASKID;
    private final int STARTTIME;
    private final int ANIMALID;
    private Task task;

    private int minutesRemaining;
    
    public Treatment(int taskID, int startTime, Task task, int ANIMALID) {
        // todo - validate input
        this.TASKID = taskID;
        this.STARTTIME = startTime;
        this.task = task;
        this.ANIMALID = ANIMALID;
    }
    public void setTask(Task task) {
        this.task = task;
    }
    public Task getTask() {
        return this.task;
    }
    public int getAnimalID() {
        return this.ANIMALID;
    }

    public int getMinutesRemaining() {
        return this.minutesRemaining;
    }

    public void setMinutesRemaining(int minutes) {
        this.minutesRemaining = minutes;
    }
    
    public int getStartTime() {
        return this.STARTTIME;
    }

    public int getTaskID() {
        return this.TASKID;
    }
}
