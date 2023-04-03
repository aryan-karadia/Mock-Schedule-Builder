package edu.ucalgary.oop;
import java.util.ArrayList;

public class Raccoon extends Animal{
    public Raccoon(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Raccoon", name, ActiveHours.NOCTURNAL, careNeeded, 5, 0, 5);
    }
}
