package edu.ucalgary.oop;
import java.util.ArrayList;

public class Orphan extends Animal {
    public Orphan(int animalID, String name, ActiveHours activeHours, ArrayList<Treatment> careNeeded, int timeToFeed) {
        super(animalID, "Orphan", name, activeHours, careNeeded, timeToFeed);
    }
}

