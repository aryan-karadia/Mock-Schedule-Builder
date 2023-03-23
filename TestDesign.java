package edu.ucalgary.oop;

import org.junit.*;
import org.junit.Before;
import static org.junit.Assert.*;


public class TestDesign {
    private Animal animal1;
    private Treatment treatment1;
    private Schedule schedule1;
    private Task testTask;

    /**
     * Set up instances of Animal and Treatment for testing purposes.
     */
    @Before
    public void setUp() {
        animal1 = new Animal(1, "Dog", "Scout", DIURNAL, careNeeded, feedingSchedule, 12);
        treatment1 = new Treatment(2, 12);
        schedule1 = new Schedule(animals[]);
        testTask = new Task(1, 30, 2, "Kit feeding");

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
    // Task tests
    @Test
    public void testTaskConstructor() throws Exception {
        try {
            assertNotNull("Task constructor does not create a Task object when given a valid task", testTask);
        }
        catch (Exception e) {
            fail("Task constructor threw an unexpected exception instead of creating new object.");
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void testTaskConstructorWithInvalidTimeWindow() throws Exception {
        try {
            Task testTask = new Task(1, 30, 25, "Kit feeding");
        } catch (Exception e) {
            fail("Task constructor throws an unexpected error when creating a Task object with an invalid time window.");
        }
    }

    @Test
    public void testGetDURATION() throws Exception {
        try {
            int expResult = 30;
            int result = testTask.getDURATION();
            assertEquals("Task getDURATION returns wrong duration time:", expResult, result);
        }
        catch (Exception e) {
            fail("Task getDURATION() throws an unexpected error.");
        }
    }

    @Test
    public void testGetTimeWindow() throws Exception {
        try {
            int expResult = 2;
            int result = testTask.getTimeWindow();
            assertEquals("Task getTimeWindow returns wrong time value:", expResult, result);
        }
        catch (Exception e) {
            fail("Task getTimeWindow() throws an unexpected error");
        }
    }

    @Test
    public void testGetDescription() throws Exception {
        try {
            String expResult = "Kit feeding";
            String result = testTask.getDescription();
            assertEquals("Task getDescription returns wrong string:", expResult, result);
        }
        catch (Exception e) {
            fail("Task getDescription throws an unexpected error");
        }
    }

    @Test
    public void testGetID() throws Exception {
        try {
            int expResult = 1;
            int result = testTask.getID();
            assertEquals("Task getID returns wrong ID:", expResult, result);
        }
        catch (Exception e) {
            fail("Task getID throws an unexpected error");
        }
    }

    @Test
    public void testFeedingHours() throws Exception {
        try {
              ArrayList<Integer> expResult = new ArrayList<Integer>(
                Arrays.asList(19, 20, 21));
              ArrayList<Int> result = testTask.feedingHours();
              assertEquals("Task feedingHours returns wrong ArrayList:", expResult, result);  
        }
        catch (Exception e) {
            fail("Task feedingHours throws an unexpected error");
        }
    }


}
