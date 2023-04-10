/**
 * This code extends the Animal class.
 * The code starts by importing "java.util.ArrayList". 
 * It then defines a class named "Raccoon" and extends the "Animal" class.
 * The constructor of the "Raccoon" class takes in an animalID, a name, 
 * and a careNeeded variable.
 * The constructor then calls the constructor of the "Animal" class and passes 
 * in the animalID, "Raccoon", name, ActiveHours.NOCTURNAL, careNeeded, 5, 0, and 5.
 * @author Aryan Karadia, Aditya Prasad, Thomas Mattern, Brock Tomlinson
 * @version 1.2
 * @since 1.0
 */
package edu.ucalgary.oop;

import java.util.ArrayList;

public class Raccoon extends Animal {

    /**
     * Constructor for Raccoon class.
     *
     * @param animalID   the ID of the raccoon
     * @param name       the name of the raccoon
     * @param careNeeded the list of treatments needed for the raccoon
     */
    public Raccoon(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Raccoon", name, ActiveHours.NOCTURNAL, careNeeded, 5, 0, 5);
    }
}
