/**
 * A class representing a Porcupine that extends the Animal class.
 * The Porcupine class has a constructor method that calls the Animal class's constructor method.
 * The Animal class's constructor method takes in an animal ID, an animal type, a name, 
 * an ActiveHours object, a list of Treatment objects, a cage size, a daily food cost, 
 * and a daily food quantity.
 * @author Aryan Karadia, Aditya Prasad, Thomas Mattern, Brock Tomlinson
 * @version 1.2
 * @since 1.0
 */
package edu.ucalgary.oop;
import java.util.ArrayList;

public class Porcupine extends Animal {
    /**
     * Constructs a Porcupine object with the given animal ID, name, and care needed list.
     * @param animalID the ID of the Porcupine
     * @param name the name of the Porcupine
     * @param careNeeded a list of treatments needed by the Porcupine
     */
    public Porcupine(int animalID, String name, ArrayList<Treatment> careNeeded) {
        super(animalID, "Porcupine", name, ActiveHours.CREPUSCULAR, careNeeded, 5, 0, 10);
    }
}
