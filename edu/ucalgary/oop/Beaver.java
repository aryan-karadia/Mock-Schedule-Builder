package edu.ucalgary.oop;
import java.util.ArrayList;

public class Beaver extends Animal {
    public Beaver(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Beaver", name, ActiveHours.DIURNAL, careNeeded, 5, 0, 5);
    }
}
