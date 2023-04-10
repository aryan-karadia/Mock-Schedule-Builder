/**
 * This code defines an abstract class named "Animal".
 * The abstract class has 14 variables and 1 constructor.
 * The variables are named "ANIMALID", "TYPE", "name", "careNeeded", "ACTIVEHOURS",
 * "POSSIBLEFEEDINGHOURS", "timeToFeed", "timeToClean", "isFed", "cageCleaned",
 * "FOODPREPTIME", and "isFoodPrepped".
 * The constructor accepts 7 parameters.
 * The Animal abstract class also has 8 methods.
 * The first method is named "getAnimalID" and returns the "ANIMALID" value.
 * The second method is named "getType" and returns the "TYPE" value.
 * The third method is named "getName" and returns the "name" value.
 * The fourth method is named "setName" and sets the "name" value to the value of 
 * the "name" parameter.
 * The fifth method is named "getCareNeeded" and returns the "careNeeded" value.
 * The sixth method is named "setCareNeeded" and sets the "careNeeded" value to the 
 * value of the "care" parameter.
 * The seventh method is named "getActiveHours" and returns the "ACTIVEHOURS" value.
 * The eighth method is named "getTimeToFeed" and returns the "timeToFeed" value.
 * The ninth method is named "setTimeToFeed" and sets the "timeToFeed" value to the 
 * value of the "timeToFeed" parameter.
 * The tenth method is named "isFed" and returns the "isFed" value.
 * The eleventh method is named "setFed" and sets the "isFed" value to the value of 
 * the "fed" parameter.
 * The twelfth method is named "isCageCleaned" and returns the "cageCleaned" value.
 * The thirteenth method is named "setCageCleaned" and sets the "cageCleaned" value 
 * to the value of the "cleaned" parameter.
 * The fourteenth method is named "isDueForFeeding" and returns a boolean value.
 * The fifteenth method is named "isDueForCleaning" and returns a boolean value.
 * @author Aryan Karadia, Aditya Prasad, Thomas Mattern, Brock Tomlinson
 * @version 1.4
 * @since 1.0
 */
package edu.ucalgary.oop;
import java.util.ArrayList;

/**
 * Animal class represents a generic animal in the zoo system.
 */
abstract class Animal {
    /**
     * The unique ID of the animal.
     */
    protected final int ANIMALID;

    /**
     * The type of the animal.
     */
    protected String type;

    /**
     * The name of the animal.
     */
    protected String name;

    /**
     * The list of treatments that the animal requires.
     */
    protected ArrayList<Treatment> careNeeded;

    /**
     * The active hours during which the animal is awake and active.
     */
    protected final ActiveHours ACTIVEHOURS;

    /**
     * The possible feeding hours of the animal based on its active hours.
     */
    protected final ArrayList<Integer> POSSIBLEFEEDINGHOURS;

    /**
     * The time it takes to feed the animal.
     */
    protected int timeToFeed;

    /**
     * The time it takes to clean the animal's cage.
     */
    protected int timeToClean;

    /**
     * Flag indicating whether the animal has been fed.
     */
    protected boolean isFed = false;

    /**
     * Flag indicating whether the animal's cage has been cleaned.
     */
    protected boolean cageCleaned = false;

    /**
     * The time it takes to prepare food for the animal.
     */
    protected final int FOODPREPTIME;

    /**
     * Flag indicating whether the animal's food has been prepared.
     */
    protected boolean isFoodPrepped = false;
    
    /**
     * Creates a new instance of the Animal class.
     * Throws IllegalArgumentException when animalID or type or name is null
     * @param animalID the unique ID of the animal.
     * @param type the type of the animal.
     * @param name the name of the animal.
     * @param activeHours the active hours during which the animal is awake and active.
     * @param careNeeded the list of treatments that the animal requires.
     * @param timeToFeed the time it takes to feed the animal.
     * @param foodPrepTime the time it takes to prepare food for the animal.
     * @param timeToClean the time it takes to clean the animal's cage.
     */
    public Animal(int animalID, String type, String name, ActiveHours activeHours,
                  ArrayList<Treatment> careNeeded, int timeToFeed, int foodPrepTime, int timeToClean) throws IllegalArgumentException {
        if (animalID < 1 || type == null || name.isEmpty()) {
            throw new IllegalArgumentException("animalID cannot be null");
        }
        this.ANIMALID = animalID;
        this.type = type;
        this.name = name;
        this.ACTIVEHOURS = activeHours;
        this.careNeeded = careNeeded;
        this.POSSIBLEFEEDINGHOURS = ACTIVEHOURS.feedingHours();
        this.timeToFeed = timeToFeed;
        this.FOODPREPTIME = foodPrepTime;
        this.timeToClean = timeToClean;
    }
    
    /**
     * Returns the unique ID of the animal.
     * @return the unique ID of the animal.
     */
    public int getAnimalID() {
        return this.ANIMALID;
    }
    
    /**
     * Returns the type of the animal.
     * @return the type of the animal.
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Returns the name of the animal.
     * @return the name of the animal.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Sets the name of the animal.
     * @param name the new name of the animal.
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the list of treatments that the animal requires.
     * @return the list of treatments that the animal requires.
     */
    public ArrayList<Treatment> getCareNeeded() {
        return this.careNeeded;
    }

    /**
     * Sets the list of treatments that the animal requires.
     * @param care the new list of treatments that the animal requires.
     */
    public void setCareNeeded(ArrayList<Treatment> care) {
        this.careNeeded = care;
    }

    /**
     * Returns the active hours during which the animal is awake and active.
     * @return the active hours during which the animal is awake and active.
     */
    public ActiveHours getActiveHours() {
        return this.ACTIVEHOURS;
    }

    /**
     * Returns the time it takes to feed the animal.
     * @return the time it takes to feed the animal.
     */
    public int getTimeToFeed() {
        return this.timeToFeed;
    }

    /**
     * Sets the time it takes to feed the animal.
     * @param timeToFeed the new time it takes to feed the animal.
     */
    public void setTimeToFeed(int timeToFeed) {
        this.timeToFeed = timeToFeed;
    }

    /**
     * Returns whether the animal has been fed or not.
     * @return true if the animal has been fed, false otherwise.
     */
    public boolean isFed() {
        return this.isFed;
    }

    /**
     * Sets whether the animal has been fed or not.
     * @param fed true if the animal has been fed, false otherwise.
     */
    public void setFed(boolean fed) {
        this.isFed = fed;
    }

    /**
     * Returns whether the animal's cage has been cleaned or not.
     * @return true if the animal's cage has been cleaned, false otherwise.
     */
    public boolean isCageCleaned() {
        return cageCleaned;
    }

    /**
     * Sets whether the animal's cage has been cleaned or not.
     * @param cleaned true if the animal's cage has been cleaned, false otherwise.
     */
    public void setCageCleaned(boolean cleaned) {
        this.cageCleaned = cleaned;
    }

    /**
     * Returns whether the food for the animal has been prepared or not.
     * @return true if the food for the animal has been prepared, false otherwise.
     */
    public boolean getIsFoodPrepped() {
        return this.isFoodPrepped;
    }

    /**
     * Sets whether the food for the animal has been prepared or not.
     * @param isFoodPrepped true if the food for the animal has been prepared, false otherwise.
     */
    public void setIsFoodPrepped(boolean isFoodPrepped) {
        this.isFoodPrepped = isFoodPrepped;
    }
    public int getTimeToClean() {
        return this.timeToClean;
    }

    public int getFoodPrepTime() {
        return this.FOODPREPTIME;
    }
}
