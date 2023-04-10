/**
 * This code creates an Orphan child class of the Animal parent class.
 * The class represents an orphan animal in the zoo.
 * @author Aryan Karadia, Aditya Prasad, Thomas Mattern, Brock Tomlinson
 * @version 1.2
 * @since 1.0
 */
package edu.ucalgary.oop;

import java.util.ArrayList;

public class Orphan extends Animal {
    
    /**
     * Constructs an Orphan object with the specified parameters.
     * 
     * @param animalID      the ID of the orphan animal
     * @param name          the name of the orphan animal
     * @param activeHours   the active hours of the orphan animal
     * @param careNeeded    the treatments needed by the orphan animal
     * @param timeToFeed    the time required to feed the orphan animal
     * @param timeToPrepFood    the time required to prepare food for the orphan animal
     * @param timeToClean   the time required to clean the orphan animal's cage
     */
    public Orphan(int animalID, String name, ActiveHours activeHours, ArrayList<Treatment> careNeeded, int timeToFeed, int timeToPrepFood, int timeToClean) {
        super(animalID, "Orphan", name, activeHours, careNeeded, timeToFeed, timeToPrepFood, timeToClean);
    }
    
    /**
     * Overrides the isFed method of the parent class and returns true always.
     * 
     * @return true always, indicating that the orphan animal is fed
     */
    @Override
    public boolean isFed() {
        return true;
    }
}
