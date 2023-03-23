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

    // Animal tests
    @Test
    public void testGetAnimalId() {
        int id = animal1.getId();
        assertEquals("Animal ID should be 1, but it's not", 1, id);
    }

    @Test
    public void testGetType() {
        String type = animal1.getType();
        assertEquals("Animal type should be 'Dog', but it's not", "Dog", type);
    }

    @Test
    public void testGetName() {
        String name = animal1.getName();
        assertEquals("Animal name should be 'Scout', but it's not", "Scout", name);
    }

    @Test
    public void testGetActivityPattern() {
        ActivityPattern activityPattern = animal1.getActivityPattern();
        assertEquals("Animal activity pattern should be DIURNAL, but it's not", ActivityPattern.DIURNAL, activityPattern);
    }

    @Test
    public void testGetCareNeeded() {
        String careNeeded = animal1.getCareNeeded();
        assertEquals("Animal care needed should be 'careNeeded', but it's not", "careNeeded", careNeeded);
    }

    @Test
    public void testGetFeedingSchedule() {
        String feedingSchedule = animal1.getFeedingSchedule();
        assertEquals("Animal feeding schedule should be 'feedingSchedule', but it's not", "feedingSchedule", feedingSchedule);
    }

    @Test
    public void testGetAge() {
        int age = animal1.getAge();
        assertEquals("Animal age should be 12, but it's not", 12, age);
    }
    @Test
    public void testGetTreatmentId() {
        int id = treatment1.getId();
        assertEquals("Treatment ID should be 2, but it's not", 2, id);
    }

    @Test
    public void testGetTreatmentAnimalId() {
        int animalId = treatment1.getAnimalId();
        assertEquals("Treatment animal ID should be 1, but it's not", 1, animalId);
    }
    // Schedule tests
    @Test
    public void testGetFormattedSchedule() {
        String formattedSchedule = schedule1.getFormattedSchedule();
        assertNotNull("Formatted schedule should not be null, but it is", formattedSchedule);
        assertTrue("Formatted schedule should contain the animal's information, but it doesn't",
            formattedSchedule.contains("Dog") && formattedSchedule.contains("Scout") && formattedSchedule.contains("DIURNAL"));
    }
    
}
