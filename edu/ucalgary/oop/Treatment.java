package edu.ucalgary.oop;

public class Treatment {
    private final int TASKID;
    private final int STARTTIME;
    private Task task;
    
    public Treatment(int taskID, int startTime) {
        this.TASKID = taskID;
        this.STARTTIME = startTime;
    }
    
    public Task getTask() {
        return this.task;
    }
    
    public int getStartTime() {
        return this.STARTTIME;
    }
    
    public int getTaskID() {
        return this.TASKID;
    }
}