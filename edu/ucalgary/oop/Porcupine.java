package edu.ucalgary.oop;
import java.util.ArrayList;

public class Porcupine extends Animal {
    public Porcupine(int animalID, String name, ActiveHours activeHours, ArrayList<Treatment> careNeeded, Schedule feedingSchedule, int timeToFeed) {
        super(animalID, "Porcupine", name, activeHours, careNeeded, feedingSchedule, timeToFeed);
    }
}
