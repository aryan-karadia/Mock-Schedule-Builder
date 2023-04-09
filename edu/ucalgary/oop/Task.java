/**
 * This code creates a task class that has 4 variables.
 * The code starts by defining a package.
 * The code then defines a "Task" class.
 * The "Task" class contains 4 private variables and a constructor.
 * The constructor takes 4 parameters.
 * The constructor sets the 4 private variables to the 4 parameters.
 * The "Task" class also contains 4 getters that return the values of the 4 private variables.
 */
package edu.ucalgary.oop;

public class Task {
    
    /**
     * The unique ID of the task.
     */
    private final int TASKID;
    
    /**
     * The duration of the task in minutes.
     */
    private final int DURATION;
    
    /**
     * The time window during which the task can be completed, in minutes.
     */
    private final int timeWindow;
    
    /**
     * A brief description of the task.
     */
    private final String description;

    /**
     * Constructor for the Task class.
     * Throws IllegalArgumentException if task id or desciption is empyty
     * @param taskID      the unique ID of the task
     * @param duration    the duration of the task in minutes
     * @param timeWindow  the time window during which the task can be completed, in minutes
     * @param description a brief description of the task
     */
    public Task(int taskID, int duration, int timeWindow, String description) throws IllegalArgumentException {
        if (taskID < 1 || description.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.TASKID = taskID;
        this.DURATION = duration;
        this.timeWindow = timeWindow;
        this.description = description;
    }
    /**
     * Returns the unique ID of the task.
     * 
     * @return the task ID
     */
    public int getID() {
        return this.TASKID;
    }

    /**
     * Returns the duration of the task in minutes.
     * 
     * @return the task duration
     */
    public int getDURATION() {
        return this.DURATION;
    }

    /**
     * Returns the time window during which the task can be completed, in minutes.
     * 
     * @return the task time window
     */
    public int getTimeWindow() {
        return this.timeWindow;
    }

    /**
     * Returns a brief description of the task.
     * 
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }
}
