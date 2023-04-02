package edu.ucalgary.oop;
import java.util.ArrayList;

abstract class Animal {
    protected final int ANIMALID;
    protected final String TYPE;
    protected String name;
    protected ArrayList<Treatment> careNeeded;
    protected ActiveHours activeHours;
    protected Schedule feedingSchedule;
    protected int timeToFeed;
    protected boolean isFed = false;
    protected boolean cageCleaned = false;
    
    public Animal(int animalID, String type, String name, ActiveHours activeHours,
                  ArrayList<Treatment> careNeeded, Schedule feedingSchedule, int timeToFeed) {
        // todo - validate input
        this.ANIMALID = animalID;
        this.TYPE = type;
        this.name = name;
        this.activeHours = activeHours;
        this.careNeeded = careNeeded;
        this.feedingSchedule = feedingSchedule;
        this.timeToFeed = timeToFeed;
    }
    
    public int getAnimalID() {
        return this.ANIMALID;
    }
    
    public String getType() {
        return this.TYPE;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<Treatment> getCareNeeded() {
        return this.careNeeded;
    }
    
    public void setCareNeeded(ArrayList<Treatment> care) {
        this.careNeeded = care;
    }
    
    public ActiveHours getActiveHours() {
        return this.activeHours;
    }
    
    public Schedule getFeedingSchedule() {
        return this.feedingSchedule;
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
    
    public boolean isDueForFeeding(int currentHour) {
    	//TODO
        return false;
    }
    
    public boolean isDueForCleaning(int currentHour) {
    	//TODO
        return false;
    }
}
