package edu.ucalgary.oop;
import java.util.ArrayList;

public class Animal {
    private final int ANIMALID;
    private final String TYPE;
    private String name;
    private ArrayList<Treatment> careNeeded;
    private ActiveHours activeHours;
    private Schedule feedingSchedule;
    private int timeToFeed;
    private boolean isFed = false;
    private boolean cageCleaned = false;
    
    public Animal(int animalID, String type, String name, ActiveHours activeHours,
                  ArrayList<Treatment> careNeeded, Schedule feedingSchedule, int timeToFeed) {
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
    	//TO DO
    }
    
    public boolean isDueForFeeding(int currentHour) {
    	//TO DO
    }
    
    public boolean isDueForCleaning(int currentHour) {
    	//TO DO
    }
}
