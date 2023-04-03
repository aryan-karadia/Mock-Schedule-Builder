package edu.ucalgary.oop;
import java.util.ArrayList;

public class Raccoon extends Animal{
    public Raccoon(int animalID, String name, ActiveHours activeHours, ArrayList<Treatment> careNeeded, int timeToFeed) {
        super(animalID, "Raccoon", name, activeHours, careNeeded, timeToFeed);
    }
}
