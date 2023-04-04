/*
This code creates a task class that has 4 variables.
The code starts by defining a package.
The code then defines a "Task" class.
The "Task" class contains 4 private variables and a constructor.
The constructor takes 4 parameters.
The constructor sets the 4 private variables to the 4 parameters.
The "Task" class also contains 4 getters that return the values of the 4 private variables.
*/
package edu.ucalgary.oop;

public class Task {
    private final int TASKID;
    private final int DURATION;
    private final int timeWindow;
    private final String description;

    public Task(int taskID, int duration, int timeWindow, String description) {
    /*
    Constructor for Task class.

    Parameters
    ----------
    taskID: int
        The task ID.
    duration: int
        The duration of the task.
    timeWindow: int
        The time window of the task.
    description: String
        The description of the task.

    Returns
    -------
    None

    */
        // todo - validate input
        this.TASKID = taskID;
        this.DURATION = duration;
        this.timeWindow = timeWindow;
        this.description = description;
    }

    public int getID() {
    /*
    Gets the ID of the task.

    Parameters
    ----------
    None

    Returns
    -------
    int
        The task ID.

    */
        return this.TASKID;
    }

    public int getDURATION() {
    /*
    Gets the duration of the task.

    Parameters
    ----------
    None

    Returns
    -------
    int
        The duration of the task.

    */
        return this.DURATION;
    }

    public int getTimeWindow() {
    /*
    Gets the time window of the task.

    Parameters
    ----------
    None

    Returns
    -------
    int
        The time window of the task.

    */
        return this.timeWindow;
    }

    public String getDescription() {
    /*
    Gets the description of the task.

    Parameters
    ----------
    None

    Returns
    -------
    String
        The description of the task.

    */
        return this.description;
    }
}
