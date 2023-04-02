package edu.ucalgary.oop;
import java.util.ArrayList;

public class Beaver extends Animal {
    public Beaver(int animalID, String name, ActiveHours activeHours, ArrayList<Treatment> careNeeded, Schedule feedingSchedule, int timeToFeed) {
        super(animalID, "Beaver", name, activeHours, careNeeded, feedingSchedule, timeToFeed);
    }
}
