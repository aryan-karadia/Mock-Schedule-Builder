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
    private final int ANIMALID;
    private Task task;

    private int minutesRemaining;

    /**
     * Constructor for the Treatment class.
     *
     * @param taskID    the ID of the task to be performed
     * @param startTime the start time of the treatment
     * @param task      the task to be performed
     */
    public Treatment(int taskID, int startTime, Task task, int ANIMALID) {
        // todo - validate input
        this.TASKID = taskID;
        this.STARTTIME = startTime;
        this.task = task;
        this.ANIMALID = ANIMALID;
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
    public int getAnimalID() {
        return this.ANIMALID;
    }

    public int getMinutesRemaining() {
        return this.minutesRemaining;
    }

    public void setMinutesRemaining(int minutes) {
        this.minutesRemaining = minutes;
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
