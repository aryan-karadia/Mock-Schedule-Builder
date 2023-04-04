/**
 * The Treatment class represents a treatment that consists of a task ID, a start 
 * time, and a task to be performed.
 */
package edu.ucalgary.oop;

public class Treatment {
    
    /**
     * The ID of the task to be performed.
     */
    private final int TASKID;
    
    /**
     * The start time of the treatment.
     */
    private final int STARTTIME;
    
    /**
     * The task to be performed.
     */
    private Task task;
    
    /**
     * Constructor for the Treatment class.
     * 
     * @param taskID    the ID of the task to be performed
     * @param startTime the start time of the treatment
     * @param task      the task to be performed
     */
    public Treatment(int taskID, int startTime, Task task) {
        // todo - validate input
        this.TASKID = taskID;
        this.STARTTIME = startTime;
        this.task = task;
    }
    
    /**
     * Sets the task to be performed.
     * 
     * @param task the task to be performed
     */
    public void setTask(Task task) {
        this.task = task;
    }
    
    /**
     * Returns the task to be performed.
     * 
     * @return the task to be performed
     */
    public Task getTask() {
        return this.task;
    }
    
    /**
     * Returns the start time of the treatment.
     * 
     * @return the start time of the treatment
     */
    public int getStartTime() {
        return this.STARTTIME;
    }

    /**
     * Returns the ID of the task to be performed.
     * 
     * @return the ID of the task to be performed
     */
    public int getTaskID() {
        return this.TASKID;
    }
}
