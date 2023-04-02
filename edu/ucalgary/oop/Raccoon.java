package edu.ucalgary.oop;
import java.util.ArrayList;

public class Raccoon extends Animal{
    public Raccoon(int animalID, String name, ActiveHours activeHours, ArrayList<Treatment> careNeeded, Schedule feedingSchedule, int timeToFeed) {
        super(animalID, "Raccoon", name, activeHours, careNeeded, feedingSchedule, timeToFeed);
    }
}
