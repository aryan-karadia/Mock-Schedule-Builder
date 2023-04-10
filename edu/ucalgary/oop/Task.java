/**
 * This code creates a task class that has 4 variables.
 * The code starts by defining a package.
 * The code then defines a "Task" class.
 * The "Task" class contains 4 private variables and a constructor.
 * The constructor takes 4 parameters.
 * The constructor sets the 4 private variables to the 4 parameters.
 * The "Task" class also contains 4 getters that return the values of the 4 private variables.
 * @author Aryan Karadia, Aditya Prasad, Thomas Mattern, Brock Tomlinson
 * @version 1.2
 * @since 1.0
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
    private final int TIMEWINDOW;
    
    /**
     * A brief DESCRIPTION of the task.
     */
    private final String DESCRIPTION;

    /**
     * Constructor for the Task class.
     * Throws IllegalArgumentException if desciption is empyty
     * @param taskID      the unique ID of the task
     * @param duration    the duration of the task in minutes
     * @param TIMEWINDOW  the time window during which the task can be completed, in minutes
     * @param DESCRIPTION a brief DESCRIPTION of the task
     */
    public Task(int taskID, int duration, int TIMEWINDOW, String DESCRIPTION) throws IllegalArgumentException {
        if (DESCRIPTION.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.TASKID = taskID;
        this.DURATION = duration;
        this.TIMEWINDOW = TIMEWINDOW;
        this.DESCRIPTION = DESCRIPTION;
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
    public int getTIMEWINDOW() {
        return this.TIMEWINDOW;
    }

    /**
     * Returns a brief DESCRIPTION of the task.
     * 
     * @return the task DESCRIPTION
     */
    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }
}
