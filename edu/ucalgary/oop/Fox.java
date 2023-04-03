package edu.ucalgary.oop;
import java.util.ArrayList;

public class Fox extends Animal {
    public Fox(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Fox", name, ActiveHours.NOCTURNAL, careNeeded,5, 5, 5);
    }
}

