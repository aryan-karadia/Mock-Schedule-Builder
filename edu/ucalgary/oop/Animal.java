package edu.ucalgary.oop;
import java.util.ArrayList;

abstract class Animal {
    protected final int ANIMALID;
    protected String type;
    protected String name;
    protected ArrayList<Treatment> careNeeded;
    protected final ActiveHours ACTIVEHOURS;
    protected final ArrayList<Integer> POSSIBLEFEEDINGHOURS;
    protected int timeToFeed;
    protected int timeToClean;
    protected boolean isFed = false;
    protected boolean cageCleaned = false;
    protected final int FOODPREPTIME;
    protected boolean isFoodPrepped = false;
    
    public Animal(int animalID, String type, String name, ActiveHours activeHours,
                  ArrayList<Treatment> careNeeded, int timeToFeed, int foodPrepTime, int timeToClean) {
        // todo - validate input
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
    
    public int getAnimalID() {
        return this.ANIMALID;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public ArrayList<Treatment> getCareNeeded() {
        return this.careNeeded;
    }
    
    public void setCareNeeded(ArrayList<Treatment> care) {
        this.careNeeded = care;
    }
    
    public ActiveHours getActiveHours() {
        return this.ACTIVEHOURS;
    }
    
    public int getTimeToFeed() {
        return this.timeToFeed;
    }
    
    public void setTimeToFeed(int timeToFeed) {
        this.timeToFeed = timeToFeed;
    }
    
    public boolean isFed() {
        return this.isFed;
    }
    
    public void setFed(boolean fed) {
        this.isFed = fed;
    }
    
    public boolean isCageCleaned() {
        return cageCleaned;
    }
    
    public void setCageCleaned(boolean cleaned) {
        this.cageCleaned = cleaned;
    }

    public boolean getIsFoodPrepped() { return this.isFoodPrepped; }
    public void setIsFoodPrepped(boolean isFoodPrepped) {
        this.isFoodPrepped = isFoodPrepped;
    }
    public int getTimeToClean() {
        return this.timeToClean;
    }

    public int getFoodPrepTime() {
        return this.FOODPREPTIME;
    }

    
    public boolean isDueForFeeding(int currentHour) {
    	//TODO
        return false;
    }
    
    public boolean isDueForCleaning(int currentHour) {
    	//TODO
        return false;
    }
}
