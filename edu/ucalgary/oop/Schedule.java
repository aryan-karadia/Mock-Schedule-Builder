package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Schedule {
    private static HashMap<Integer, String> workingHours = new HashMap<>(24);
    private boolean backupVolunteerNeeded = false;
    private ArrayList<Animal> animals;
    private HashMap<Integer, ArrayList<Task>> tasks = new HashMap<>(24);

    public Schedule(Animal[] animals) {
        this.animals = new ArrayList<>(Arrays.asList(animals));
    }

    public boolean getBackupNeeded() {
        return this.backupVolunteerNeeded;
    }

    public void setBackupNeeded(boolean needed) {
        this.backupVolunteerNeeded = needed;
    }

    public void scheduleFeedingAndCleaningTasks() {
        //  todo
    }

    public String getFormattedSchedule() {
        // todo
        return null;
    }

    public void addTask(int hour, Task task) {
    	//todo
    }

    public void removeTask(int hour, Task task) {
    	//todo
    }
}