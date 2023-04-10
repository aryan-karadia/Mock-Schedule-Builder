/**
 * This code creates a Beaver class that extends the Animal class.
 * The code starts by defining its package.
 * The code then imports the java.util.ArrayList package.
 * The code then defines a Beaver class that extends the Animal class.
 * The code then has a constructor that calls the super constructor and passes in values for the animalID, species, name, activeHours, careNeeded, minSpace, minCare, and maxCare.
 * @author Aryan Karadia, Aditya Prasad, Thomas Mattern, Brock Tomlinson
 * @version 1.2
 * @since 1.0
 */
package edu.ucalgary.oop;

import java.util.ArrayList;

public class Beaver extends Animal {
    
    /**
     * Constructor for creating a Beaver object
     * @param animalID unique ID for the beaver
     * @param name name of the beaver
     * @param careNeeded list of treatments needed by the beaver
     */
    public Beaver(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Beaver", name, ActiveHours.DIURNAL, careNeeded, 5, 0, 5);
    }
    
}
