package edu.ucalgary.oop;

import org.junit.*;
import org.junit.Before;
import static org.junit.Assert.*;


public class TestDesign {
    private Animal animal1;
    private Treatment treatment1;
    private Schedule schedule1;

    /**
     * Set up instances of Animal and Treatment for testing purposes.
     */
    @Before
    public void setUp() {
        animal1 = new Animal(1, "Dog", "Scout", DIURNAL, careNeeded, feedingSchedule, 12);
        treatment1 = new Treatment(2, 12);
        schedule1 = new Schedule(animals[]);
    }
