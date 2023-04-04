package edu.ucalgary.oop;
import java.lang.reflect.Array;
import java.util.*;

public class Schedule {
    private HashMap<Integer, Boolean> backupVolunteerNeeded = new HashMap<>(24);
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
                    setBackupNeeded(startTime, true);
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

    // gets backup for specified hour
    // @param hour - checks hour specified if it needs a backup volunteer
    // @return - returns value if backup volunteer is needed or not
    public boolean getBackupNeeded(int hour) {
        return this.backupVolunteerNeeded.get(hour);
    }

    // sets backup volunteer status for given hour
    // @param hour - hour in which to set the value for
    // @param needed - bool value needed to change
    public void setBackupNeeded(int hour, boolean needed) {
        this.backupVolunteerNeeded.put(hour, needed);
    }

    // Finds all available time and schedules feeding and cleaning
    public void scheduleFeedingAndCleaningTasks() {
        // finds amount of each type of animal
        int amountOfCoyotes = 0;
        for (Animal animal : this.animals) {
            if (Objects.equals(animal.getType(), "coyote")) {
                amountOfCoyotes++;
            }
        }
        int amountOfFoxs = 0;
        for (Animal animal : this.animals) {
            if (Objects.equals(animal.getType(), "fox")) {
                amountOfFoxs++;
            }
        }
        int amountOfBeavers = 0;
        for (Animal animal : this.animals) {
            if (Objects.equals(animal.getType(), "beaver")) {
                amountOfBeavers++;
            }
        }
        int amountOfPorcupines = 0;
        for (Animal animal : this.animals) {
            if (Objects.equals(animal.getType(), "porcupine")) {
                amountOfPorcupines++;
            }
        }
        int amountOfRaccoons = 0;
        for (Animal animal : this.animals) {
            if (Objects.equals(animal.getType(), "raccoon")) {
                amountOfRaccoons++;
            }
        }
        while (true) {
            ArrayList<Integer> availableHours = new ArrayList<>(24);
            // finds all available hours
            for (Map.Entry<Integer, Integer> entry : this.availableMinutes.entrySet()) {
                if (entry.getValue() != 0) {
                    availableHours.add(entry.getKey());
                }
            }
            // for every available hour it finds how much time is remaining and what animals still need to be fed
            for ( Integer hour : availableHours) {
                // coyotes first
                ArrayList<Integer> availableMinutes = new ArrayList<>(3);
                if (hour == 7) {
                    // checks how much time is in each hour in Crepuscular ActiveHours
                    availableMinutes.add(this.availableMinutes.get(hour));
                    availableMinutes.add(this.availableMinutes.get(hour + 1));
                    availableMinutes.add(this.availableMinutes.get(hour + 2));
                }
                int hourChosen = findMinIndex(availableMinutes);
                // need to check how many can be done within that hour


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
            backupVolunteerNeeded.put(i, false);
        }
    }
    // Finds the index of the minimum value
    // @param al - ArrayList in which to search
    // @return index - returns the index of the minimum value
    private int findMinIndex(ArrayList<Integer> al) {
        int min = al.get(0);
        int minIndex = 0;

        for (int i = 1; i < al.size(); i++) {
            if (al.get(i) < min) {
                min = al.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        // testing stuff
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