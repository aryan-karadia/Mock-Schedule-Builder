package edu.ucalgary.oop;

import java.util.ArrayList;

public class Coyote extends Animal {
    public Coyote(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Coyote", name, ActiveHours.CREPUSCULAR , careNeeded, 5, 10, 5);
    }
}

