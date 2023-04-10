/**
 * The Coyote class extends the Animal class to represent a coyote.
 * The coyote is a diurnal animal.
 * The class has a constructor that initializes its attributes and calls the super constructor to initialize inherited attributes.
 * @author Aryan Karadia, Aditya Prasad, Thomas Mattern, Brock Tomlinson
 * @version 1.2
 * @since 1.0
 */
package edu.ucalgary.oop;

import java.util.ArrayList;

public class Coyote extends Animal {
    
    /**
     * Constructs a Coyote object with given attributes.
     * @param animalID unique identifier for the coyote
     * @param name name of the coyote
     * @param careNeeded list of treatments needed for the coyote
     */
    public Coyote(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Coyote", name, ActiveHours.CREPUSCULAR , careNeeded, 5, 10, 5);
    }
}
