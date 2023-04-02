package edu.ucalgary.oop;

import java.util.ArrayList;

public class Coyote extends Animal {
    public Coyote(int animalID, String name, ActiveHours activeHours, ArrayList<Treatment> careNeeded, Schedule feedingSchedule, int timeToFeed) {
        super(animalID, "Coyote", name, activeHours, careNeeded, feedingSchedule, timeToFeed);
    }
}
