package edu.ucalgary.oop;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private boolean backupVolunteerNeeded = false;
    private ArrayList<Animal> animals;
    private HashMap<Integer, ArrayList<Treatment>> tasks = new HashMap<>(24);

    private HashMap<Integer, Integer> availableMinutes = new HashMap<>(24);

    // Creates schedule object and schedules mandatory medical tasks
    // @param animals - ArrayList of all animals in the rescue center
    public Schedule(ArrayList<Animal> animals) throws IllegalArgumentException {
        generateDefaultSchedule();
        this.animals = new ArrayList<>(animals);
        // for every animal, find what time the tasks are required to be done at
        for (Animal animal: animals) {
            for (Treatment treatment : animal.getCareNeeded()) {
                // Checks if time is available
                int startTime = treatment.getStartTime();
                // if time is taken and available minutes is 0
                if (tasks.get(startTime) != null && availableMinutes.get(startTime) == 0) {
                    // todo - GUI element which tells person to move a certain task
                }
                // if time is taken but available minute is not 0, need to call backup volunteer
                else if (tasks.get(startTime) != null) {
                    setBackupNeeded(true);


                }
                // else time is available
                else {
                    // update available minutes for given hour
                    if (availableMinutes.get(startTime) - treatment.getTask().getDURATION() < 0) {
                        // Not enough time left in the hour to do treatment
                        // todo - GUI element to tell person to move one of the tasks
                    }
                    else {
                        // puts treatment into ArrayList of treatments which correspond to that hour
                        tasks.get(startTime).add(treatment);
                        availableMinutes.put(startTime, availableMinutes.get(startTime) - treatment.getTask().getDURATION());
                    }
                }
            }
        }
        // schedules feeding
        scheduleFeedingAndCleaningTasks();
    }

    public boolean getBackupNeeded() {
        return this.backupVolunteerNeeded;
    }

    public void setBackupNeeded(boolean needed) {
        this.backupVolunteerNeeded = needed;
    }

    // Finds all available time and schedules feeding and cleaning
    public void scheduleFeedingAndCleaningTasks() {
        while (true) {
            ArrayList<Integer> availabaleHours = new ArrayList<>(24);
            for (Map.Entry<Integer, Integer> entry : this.availableMinutes.entrySet()) {
                if (entry.getValue() != 0) {
                    availabaleHours.add(entry.getKey());
                }

            }
        }
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

    public ArrayList<Animal> getAnimals() {
        return this.animals;
    }

    /** Helper functions **/

    // Sets all values for workingHours to null for every hour and available minutes in each hour as 60
    private void generateDefaultSchedule() {
        for (int i = 0; i < 24; i++) {
            tasks.put(i, null);
            availableMinutes.put(i, 60);
        }
    }
    public static void main(String[] args) {
        ArrayList<Treatment> lonerTreatments = new ArrayList<>();
        Task eyedrops = new Task(9, 25, 1, "Eyedrops");
        lonerTreatments.add(new Treatment(9, 22, eyedrops));

        Coyote loner = new Coyote(1, "loner", lonerTreatments);
        System.out.println(loner.getClass());
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(loner);

        Schedule testSchedule = new Schedule(animals);
        System.out.println(testSchedule.getAnimals());
    }
}