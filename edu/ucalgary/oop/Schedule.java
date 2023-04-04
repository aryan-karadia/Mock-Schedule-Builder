package edu.ucalgary.oop;
import java.util.*;

public class Schedule {
    private HashMap<Integer, Boolean> backupVolunteerNeeded = new HashMap<>(24);
    private ArrayList<Animal> animals;
    private HashMap<Integer, Animal> animalMap = new HashMap<>();

    private HashMap<Integer, ArrayList<Treatment>> tasks = new HashMap<>(24);

    private HashMap<Integer, Integer> availableMinutes = new HashMap<>(24);
    private ArrayList<Integer> animalsNotFed = new ArrayList<>();

    // Creates schedule object and schedules mandatory medical tasks
    // @param animals - ArrayList of all animals in the rescue center
    public Schedule(ArrayList<Animal> animals, HashMap<Integer, Animal> animalMap) throws IllegalArgumentException {
        generateDefaultSchedule();
        this.animalMap = animalMap;
        this.animals = new ArrayList<>(animals);
        // Checks if animals have any care needed
        for (Animal animal : animals) {
            if (animal.getCareNeeded().size() == 0) {
                throw new IllegalArgumentException("Animal " + animal.getName() + " has no care needed");
            }
            // adds animal to animalsNotFed
            animalsNotFed.add(animal.getAnimalID());
        }
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
                        availableMinutes.put(startTime, availableMinutes.get(startTime) -
                                treatment.getTask().getDURATION());
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
        // coyotes first
        putAnimalFeedingIntoSchedule(ActiveHours.CREPUSCULAR, amountOfCoyotes, 10, "coyote");
        // foxes second
        putAnimalFeedingIntoSchedule(ActiveHours.NOCTURNAL, amountOfFoxs, 5, "fox");
        // beavers third
        putAnimalFeedingIntoSchedule(ActiveHours.DIURNAL, amountOfBeavers, 0, "beaver");
        // porcupines fourth
        putAnimalFeedingIntoSchedule(ActiveHours.CREPUSCULAR, amountOfPorcupines, 0, "porcupine");
        // raccoons fifth
        putAnimalFeedingIntoSchedule(ActiveHours.NOCTURNAL, amountOfRaccoons, 0, "raccoon");

        // schedules cleaning

        for (Animal animal : this.animals) {
            // For every animal, schedules cleaning if the cage is not clean
            for (int i = 0; i < 24; i++) {
                if (availableMinutes.get(i) >= 10) {
                    Task cleaning = new Task(0, animal.getTimeToClean(), 24,
                            String.format("Cleaning %s's cage", animal.getName()));
                    Treatment cleaningTreatment = new Treatment(-1, i, cleaning, animal.getAnimalID());
                    tasks.get(i).add(cleaningTreatment);
                    animal.setCageCleaned(true);
                }
            }
        }
    }

    // Recursively puts feeding tasks for all the animals into the schedule where there is room
    // @param activeHours - ActiveHours object which contains the hours in which the animal is active
    // @param amountOfAnimals - amount of animals of the same type
    // @param prepTime - time it takes to prepare the food
    // @param animalType - type of animal
    private void putAnimalFeedingIntoSchedule(ActiveHours activeHours, int amountOfAnimals, int prepTime,
                                              String animalType) {
        // base case
        if (amountOfAnimals == 0) {
            return;
        }
        int hour = activeHours.feedingHours().get(0);
        ArrayList<Integer> availableMinutes = new ArrayList<>();
        // checks how much time is in each hour in nocturnal ActiveHours
        availableMinutes.add(this.availableMinutes.get(hour));
        availableMinutes.add(this.availableMinutes.get(hour + 1));
        availableMinutes.add(this.availableMinutes.get(hour + 2));

        int hourChosen = findMinIndex(availableMinutes);
        // need to check how many can be done within that hour
        int amountOfAnimalsThatCanBeFed = (this.availableMinutes.get(hourChosen) - prepTime) / 5;
        if (amountOfAnimalsThatCanBeFed > amountOfAnimals) {
            amountOfAnimalsThatCanBeFed = amountOfAnimals;
        }
        int timeTaken = prepTime + amountOfAnimalsThatCanBeFed * 5;
        // adds feeding task to schedule
        Task task = new Task(0, timeTaken, 24, String.format("feeding %d %ss",
                amountOfAnimalsThatCanBeFed, animalType));
        Treatment treatment = new Treatment(0, hourChosen, task, 0);
        tasks.get(hourChosen).add(treatment);
        amountOfAnimals -= amountOfAnimalsThatCanBeFed;
        // updates available minutes
        this.availableMinutes.put(hourChosen, this.availableMinutes.get(hourChosen) - timeTaken);
        // calls function again to see if there are any more animals that need to be fed
        putAnimalFeedingIntoSchedule(activeHours, amountOfAnimals, prepTime, animalType);
    }

    // Prints the schedule in a readable format and returns it as a string
    // @return - String representation of the schedule
    public String getFormattedSchedule() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            if (backupVolunteerNeeded.get(i)) {
                output.append(String.format("%d:00 [+ Backup volunteer needed]\n", i));
                continue;
            }
            else {
                output.append(String.format("%d:00 \n", i));
            }
            if (tasks.get(i) == null) {
                output.append("No tasks scheduled");
            } else {
                for (Treatment treatment : tasks.get(i)) {
                    Animal animal = animalMap.get(treatment.getAnimalID());
                    // if the task is a feeding task
                    if (treatment.getTaskID() == 0) {
                        output.append(String.format(" * %s", treatment.getTask().getDescription()));
                    }
                    // if the task is a cleaning task
                    else if (treatment.getTaskID() == -1) {
                        output.append(String.format(" * %s (%s)", treatment.getTask().getDescription(), animal.getName()));
                    }
                    // if the task is a medical task
                    else {
                        output.append(String.format(" * %s (%s)", treatment.getTask().getDescription(), animal.getName()));
                    }
                }
            }
            output.append("\n");
        }
        return output.toString();
    }

    // Creates a file with the schedule in it
    public void createScheduleFile() {

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
        lonerTreatments.add(new Treatment(9, 22, eyedrops, 1));

        Coyote loner = new Coyote(1, "loner", lonerTreatments);
        System.out.println(loner.getClass());
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(loner);

    }
}