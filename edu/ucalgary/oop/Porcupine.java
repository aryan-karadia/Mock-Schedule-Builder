package edu.ucalgary.oop;
import java.util.ArrayList;

public class Porcupine extends Animal {
    public Porcupine(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Porcupine", name, ActiveHours.CREPUSCULAR, careNeeded, 5, 0, 10);
    }
}

