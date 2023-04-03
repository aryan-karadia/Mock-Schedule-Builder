package edu.ucalgary.oop;
import java.util.ArrayList;

public class Fox extends Animal {
    public Fox(int animalID, String name, ActiveHours activeHours, ArrayList<Treatment> careNeeded, int timeToFeed) {
        super(animalID, "Fox", name, activeHours, careNeeded, timeToFeed);
    }
}

