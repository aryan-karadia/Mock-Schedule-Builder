package edu.ucalgary.oop;
import java.time.LocalDate;
import java.util.*;
import java.io.*;

public class Schedule {
    private HashMap<Integer, Boolean> backupVolunteerNeeded = new HashMap<>(24);

    private HashMap<Integer, Integer> backupAvailableMinutes = new HashMap<>(24);
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
                // checks if there is enough time to do the treatment. If not enough time calls GUI else keeps checking
                if ((availableMinutes.get(startTime) + backupAvailableMinutes.get(startTime)) - treatment.getTask().getDURATION() < 0) {
                    if(backupVolunteerNeeded.containsValue(true)) {
                        // Not enough time left in the hour to do treatment
                        // todo - GUI element to tell person to move one of the tasks
                        continue;
                    }
                    else {
                        // calls backup volunteer
                        setBackupNeeded(startTime, true);
                        tasks.get(startTime).add(treatment);
                        treatment.setMinutesRemaining(availableMinutes.get(startTime));
                    }
                }
                else {
                    // puts treatment into ArrayList of treatments which correspond to that hour
                    // needs to check if backup is being used
                    if ((availableMinutes.get(startTime) - treatment.getTask().getDURATION() < 0)) {
                        // backup is being used
                        tasks.get(startTime).add(treatment);
                        treatment.setMinutesRemaining(backupAvailableMinutes.get(startTime) - treatment.getTask().getDURATION());
                        backupAvailableMinutes.put(startTime, backupAvailableMinutes.get(startTime) -
                                treatment.getTask().getDURATION());
                    }
                    else {
                        // no backup needed
                        tasks.get(startTime).add(treatment);
                        treatment.setMinutesRemaining(availableMinutes.get(startTime) - treatment.getTask().getDURATION());
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
        // updating backup minutes
        for (int i = hour; i < 24; i++) {
            backupAvailableMinutes.put(i, 60);
        }
    }

    // Finds all available time and schedules feeding and cleaning
    public void scheduleFeedingAndCleaningTasks() {
        // finds amount of each type of animal
        int amountOfCoyotes = 0;
        int amountOfFoxs = 0;
        int amountOfBeavers = 0;
        int amountOfPorcupines = 0;
        int amountOfRaccoons = 0;


        for (Animal animal : this.animals) {
            switch (animal.getType().toLowerCase()) {
                case "coyote":
                    amountOfCoyotes++;
                    break;
                case "fox":
                    amountOfFoxs++;
                    break;
                case "porcupine":
                    amountOfPorcupines++;
                    break;
                case "beaver":
                    amountOfBeavers++;
                    break;
                case "raccoon":
                    amountOfRaccoons++;
                    break;
                default:
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
            // finds the hour with the most available minutes
            int hourChosen = 0;
            for (Integer hour : availableMinutes.keySet()) {
                if (availableMinutes.get(hour) > availableMinutes.get(hourChosen)) {
                    hourChosen = hour;
                }
            }
            // updates available minutes
            if (!animal.isCageCleaned()) {
                Task cleaning = new Task(0, animal.getTimeToClean(), 24,
                        String.format("Cleaning %s's cage", animal.getName()));
                Treatment cleaningTreatment = new Treatment(-1, hourChosen, cleaning, animal.getAnimalID());
                cleaningTreatment.setMinutesRemaining(availableMinutes.get(hourChosen) - animal.getTimeToClean());
                tasks.get(hourChosen).add(cleaningTreatment);
                animal.setCageCleaned(true);
                availableMinutes.put(hourChosen, availableMinutes.get(hourChosen) - animal.getTimeToClean());
            }
        }
    }

    // Prints the schedule in a readable format and returns it as a string
    // @return - String representation of the schedule
    public String getFormattedSchedule() {
        StringBuilder output = new StringBuilder();
        output.append("Schedule for " + LocalDate.now() + ":\n" );
        for (int i = 0; i < 24; i++) {
            if (backupVolunteerNeeded.get(i)) {
                output.append(String.format("%d:00 [+ Backup volunteer needed]\n", i));
            }
            else {
                output.append(String.format("%d:00 \n", i));
            }
            if (tasks.get(i).isEmpty()) {
                output.append(" *** No tasks scheduled ***");
            } else {
                for (Treatment treatment : tasks.get(i)) {
                    Animal animal = animalMap.get(treatment.getAnimalID());
                    // if the task is a feeding task
                    if (treatment.getTaskID() == 0) {
                        output.append(String.format(" * %s", treatment.getTask().getDescription()));
                    }
                    // if the task is a cleaning task
                    else if (treatment.getTaskID() == -1) {
                        output.append(String.format(" * %s", treatment.getTask().getDescription()));
                    }
                    // if the task is a medical task
                    else {
                        output.append(String.format(" * %s (%s)", treatment.getTask().getDescription(), animal.getName()));
                    }
                    output.append("\n");
                }
            }
            output.append("\n");
        }
        return output.toString();
    }

    // Creates a file with the schedule in it
    public void createScheduleFile(){
        Formatter output = null;

        try {
            output = new Formatter("schedule.txt");
        }
        catch (SecurityException e) {
            System.err.println("You do not have write access to this file.");
            System.exit(1);
        }
        catch (FileNotFoundException e) {
            System.err.println("Error opening the file.");
            System.exit(1);
        }

        try {
            output.format("%s", getFormattedSchedule());
        }
        catch (FormatterClosedException e) {
            System.err.println("Error writing to file.");
            return;
        }
        finally {
            output.close();
        }
    }

    public ArrayList<Animal> getAnimals() {
        return this.animals;
    }

    /** Helper functions **/

    // Finds time to put feeding tasks in the schedule for all the animals into the schedule where there is room
    // @param activeHours - ActiveHours object which contains the hours in which the animal is active
    // @param amountOfAnimals - amount of animals of the same type
    // @param prepTime - time it takes to prepare the food
    // @param animalType - type of animal
    private void putAnimalFeedingIntoSchedule(ActiveHours activeHours, int amountOfAnimals, int prepTime,
                                              String animalType) {
        int animalsLeft = amountOfAnimals;
        while (animalsLeft > 0) {
            int hour = activeHours.feedingHours().get(0);
            ArrayList<Integer> availableMinutes = new ArrayList<>();
            // checks how much time is in each hour in given ActiveHours
            availableMinutes.add(this.availableMinutes.get(hour));
            availableMinutes.add(this.availableMinutes.get(hour + 1));
            availableMinutes.add(this.availableMinutes.get(hour + 2));

            int hourChosen = hour + findMaxIndex(availableMinutes);
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
            treatment.setMinutesRemaining(this.availableMinutes.get(hourChosen) - timeTaken);
            tasks.get(hourChosen).add(treatment);
            // updates amount of animals
            animalsLeft = animalsLeft - amountOfAnimalsThatCanBeFed;
            // updates available minutes
            this.availableMinutes.put(hourChosen, this.availableMinutes.get(hourChosen) - timeTaken);
        }
    }

    // Sets all values for workingHours to null for every hour and available minutes in each hour as 60
    private void generateDefaultSchedule() {
        for (int i = 0; i < 24; i++) {
            tasks.put(i, new ArrayList<>());
            availableMinutes.put(i, 60);
            backupVolunteerNeeded.put(i, false);
            backupAvailableMinutes.put(i, 0);
        }
    }
    // Finds the index of the minimum value
    // @param al - ArrayList in which to search
    // @return index - returns the index of the minimum value
    private int findMaxIndex(ArrayList<Integer> al) {
        int max = al.get(0);
        int maxIndex = 0;

        for (int i = 1; i < al.size(); i++) {
            if (al.get(i) > max) {
                max = al.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}