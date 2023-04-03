package edu.ucalgary.oop;
import java.util.ArrayList;

public class Orphan extends Animal {
    public Orphan(int animalID, String name, ActiveHours activeHours, ArrayList<Treatment> careNeeded, int timeToFeed, int timeToPrepFood, int timeToClean) {
        super(animalID, "Orphan", name, activeHours, careNeeded, timeToFeed, timeToPrepFood, timeToClean);
        }
        @Override
    public boolean isFed() {
        return true;
        }
}

