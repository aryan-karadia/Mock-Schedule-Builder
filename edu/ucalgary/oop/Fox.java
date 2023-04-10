/**
 * This class represents a Fox, which is an Animal that extends the Animal class.
 * The class contains a constructor that initializes a Fox object with a given animalID, name, and careNeeded.
 * @author Aryan Karadia, Aditya Prasad, Thomas Mattern, Brock Tomlinson
 * @version 1.2
 * @since 1.0
 */
package edu.ucalgary.oop;

import java.util.ArrayList;

public class Fox extends Animal {
    
    /**
     * Constructs a new Fox object with the given animalID, name, and careNeeded.
     * 
     * @param animalID the unique ID of the Fox
     * @param name the name of the Fox
     * @param careNeeded an ArrayList of Treatments that the Fox requires
     */
    public Fox(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Fox", name, ActiveHours.NOCTURNAL, careNeeded,5, 5, 5);
    }
}
