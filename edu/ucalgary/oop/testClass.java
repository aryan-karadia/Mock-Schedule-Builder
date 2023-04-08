/*
This code tests the inheritance of classes.
The code starts by importing the packages it needs.
The code then defines a testClass class.
The class has a testInheritance method, a taskInheritanceRelationship method, a testAnimalCareNeededAssignment method, and a testAnimalCareNeededContents method.
The testInheritance method tests whether or not an object can inherit from another object.
The testInheritance method creates a coyote object and tests if it has inherited properties from the animal object.
The taskInheritanceRelationship method tests whether or not an object can inherit properties from another object within its own object.
The taskInheritanceRelationship method creates a task object and a treatment object and tests if the treatment object has inherited properties from the task object.
The testAnimalCareNeededAssignment method tests if an array list has been assigned to an object.
The testAnimalCareNeededAssignment method creates a coyote object and tests if it has the correct array list assigned to it.
The testAnimalCareNeededContents method tests if an array list has been assigned to an object and if it contains the expected object.
The testAnimalCareNeededContents method creates a coyote object and tests if it has the correct array list assigned to it and if the array list contains the expected object.
*/
package edu.ucalgary.oop;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

public class testClass {

	@Test
	public void testInheritance() {
		ArrayList<Treatment> careNeeded = new ArrayList<>();
        Coyote animal1 = new Coyote(1, "Jared", careNeeded);

        // Test if the animal object is created and coyote can access method
        assertEquals("Animal ID mismatch", 1, animal1.getAnimalID());
        assertEquals("Animal type mismatch", "Coyote", animal1.getType());
        assertEquals("Animal name mismatch", "Jared", animal1.getName());
        assertEquals("Animal active hours mismatch", ActiveHours.CREPUSCULAR, animal1.getActiveHours());
        assertEquals("Animal care needed mismatch", careNeeded, animal1.getCareNeeded());
        assertEquals("Animal time to feed mismatch", 5, animal1.getTimeToFeed());
        assertEquals("Animal food prep time mismatch", 10, animal1.FOODPREPTIME);
        assertEquals("Animal time to clean mismatch", 5, animal1.timeToClean);
	}
	// testing if Porcupine extends animal
	@Test
	public void testPorcupine() {
		ArrayList<Treatment> careNeeded = new ArrayList<>();
		Porcupine animal1 = new Porcupine(1, "Jared", careNeeded);

        // Test if the animal object is created and coyote can access method
        assertEquals("Animal ID mismatch", 1, animal1.getAnimalID());
        assertEquals("Animal type mismatch", "Porcupine", animal1.getType());
        assertEquals("Animal name mismatch", "Jared", animal1.getName());
        assertEquals("Animal active hours mismatch", ActiveHours.CREPUSCULAR, animal1.getActiveHours());
        assertEquals("Animal care needed mismatch", careNeeded, animal1.getCareNeeded());
        assertEquals("Animal time to feed mismatch", 5, animal1.getTimeToFeed());
        assertEquals("Animal food prep time mismatch", 0, animal1.FOODPREPTIME);
        assertEquals("Animal time to clean mismatch", 10, animal1.timeToClean);
	}
	// testing if Raccoon extends animal
	@Test
	public void testRaccoon() {
		ArrayList<Treatment> careNeeded = new ArrayList<>();
		Raccoon animal1 = new Raccoon(1, "Jared", careNeeded);

        // Test if the animal object is created and coyote can access method
        assertEquals("Animal ID mismatch", 1, animal1.getAnimalID());
        assertEquals("Animal type mismatch", "Raccoon", animal1.getType());
        assertEquals("Animal name mismatch", "Jared", animal1.getName());
        assertEquals("Animal active hours mismatch", ActiveHours.NOCTURNAL, animal1.getActiveHours());
        assertEquals("Animal care needed mismatch", careNeeded, animal1.getCareNeeded());
        assertEquals("Animal time to feed mismatch", 5, animal1.getTimeToFeed());
        assertEquals("Animal food prep time mismatch", 0, animal1.FOODPREPTIME);
        assertEquals("Animal time to clean mismatch", 5, animal1.timeToClean);
	}
	// testing if Beaver extends animal
	@Test
	public void testBeaver() {
		ArrayList<Treatment> careNeeded = new ArrayList<>();
		Beaver animal1 = new Beaver(1, "Jared", careNeeded);

        // Test if the animal object is created and coyote can access method
        assertEquals("Animal ID mismatch", 1, animal1.getAnimalID());
        assertEquals("Animal type mismatch", "Beaver", animal1.getType());
        assertEquals("Animal name mismatch", "Jared", animal1.getName());
        assertEquals("Animal active hours mismatch", ActiveHours.DIURNAL, animal1.getActiveHours());
        assertEquals("Animal care needed mismatch", careNeeded, animal1.getCareNeeded());
        assertEquals("Animal time to feed mismatch", 5, animal1.getTimeToFeed());
        assertEquals("Animal food prep time mismatch", 0, animal1.FOODPREPTIME);
        assertEquals("Animal time to clean mismatch", 5, animal1.timeToClean);
	}
    // Test if the treatment object is created with the correct properties
    // Test if the task object is assigned to the treatment object correctly
    @Test
    public void taskInheritanceRelationship() {
        Task task1 = new Task(1, 5, 3, "Grooming");
        Treatment treatment1 = new Treatment(1, 3, task1,1);

        assertEquals("TaskID mismatch",1, treatment1.getTaskID());
        assertEquals("Start time mismatch",3, treatment1.getStartTime());
        assertNotNull("Null Task",treatment1.getTask());

        assertEquals("TaskID mismatch",task1.getID(), treatment1.getTask().getID());
        assertEquals("Task Duration mismatch",task1.getDURATION(), treatment1.getTask().getDURATION());
        assertEquals("Task time window mismatch",task1.getTimeWindow(), treatment1.getTask().getTimeWindow());
        assertEquals("Description mismatch",task1.getDescription(), treatment1.getTask().getDescription());
    }
    // Test if the animal object has the correct careNeeded list assigned
    @Test
    public void testAnimalCareNeededAssignment() {
        ArrayList<Treatment> careNeeded = new ArrayList<>();
        Coyote animal1 = new Coyote(1, "Jared", careNeeded);
        assertEquals("Incorrect careNeeded was returned",careNeeded, animal1.getCareNeeded());
    }
    // Test if the animal object's careNeeded list contains the expected Treatment object
    @Test
    public void testAnimalCareNeededContents() {
        Task task1 = new Task(1, 5, 3, "Grooming");
        Treatment treatment1 = new Treatment(1, 3, task1,1);
        ArrayList<Treatment> careNeeded = new ArrayList<>();
        careNeeded.add(treatment1);
        Coyote animal1 = new Coyote(1, "Jared", careNeeded);
        assertEquals("Unexpected amount of treatments",1, animal1.getCareNeeded().size());
        assertSame("Incorrect first treatment",treatment1, animal1.getCareNeeded().get(0));
    }
    // Test if the enum object has the correct feeding hours 
    @Test
    public void testEnumClass() {
        ArrayList<Treatment> careNeeded = new ArrayList<>();
        Coyote animal1 = new Coyote(1, "Jared", careNeeded);

        // Test if the Coyote object has the correct ActiveHours
        assertEquals(ActiveHours.CREPUSCULAR, animal1.getActiveHours());

        // Test if the Coyote object has the correct feeding hours
        ArrayList<Integer> expectedFeedingHours = new ArrayList<>();
        expectedFeedingHours.add(19);
        expectedFeedingHours.add(20);
        expectedFeedingHours.add(21);

        assertEquals("Enum class gave wrong active feeding hours",expectedFeedingHours, animal1.getActiveHours().feedingHours());
    }

    @Test
    public void testBackupVol() {
        ArrayList<Treatment> careNeeded = new ArrayList<>();
        Task task1 = new Task(1, 30, 3, "Grooming");
        Treatment treatment1 = new Treatment(1, 3, task1,1);
        Treatment treatment2 = new Treatment(1, 3, task1,2);
        Treatment treatment3 = new Treatment(1, 3, task1,3);

        careNeeded.add(treatment1);
        careNeeded.add(treatment2);
        careNeeded.add(treatment3);

        
        Coyote animal1 = new Coyote(1, "Jared", careNeeded);
        Coyote animal2 = new Coyote(2, "John", careNeeded);
        Coyote animal3 = new Coyote(3, "Jones", careNeeded);
        ArrayList<Animal> animals = new ArrayList<>();
        HashMap<Integer, Animal> animalMap = new HashMap<>();
        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);
        animalMap.put(animal1.getAnimalID(), animal1);
        animalMap.put(animal2.getAnimalID(), animal2);
        animalMap.put(animal3.getAnimalID(), animal3);
        Schedule schedule = new Schedule(animals, animalMap);
        assertTrue("Incorrect logic in getBackupNeeded",schedule.getBackupNeeded(3));
    }
    // testing blank schedule
    @Test
    public void generatingNewSchedule() {
    	Schedule schedule = new Schedule();
    	String expectedSchedule = "Schedule for 2023-04-06:\n" +
    			"0:00 \n *** No tasks scheduled ***\n" +
    			"1:00 \n *** No tasks scheduled ***\n" +
    			"2:00 \n *** No tasks scheduled ***\n" +
    			"3:00 \n *** No tasks scheduled ***\n" +
    			"4:00 \n *** No tasks scheduled ***\n" +
    			"5:00 \n *** No tasks scheduled ***\n" +
    			"6:00 \n *** No tasks scheduled ***\n" +
    			"7:00 \n *** No tasks scheduled ***\n" +
    			"8:00 \n *** No tasks scheduled ***\n" +
    			"9:00 \n *** No tasks scheduled ***\n" +
    			"10:00 \n *** No tasks scheduled ***\n" +
    			"11:00 \n *** No tasks scheduled ***\n" +
    			"12:00 \n *** No tasks scheduled ***\n" +
    			"13:00 \n *** No tasks scheduled ***\n" +
    			"14:00 \n *** No tasks scheduled ***\n" +
    			"15:00 \n *** No tasks scheduled ***\n" +
    			"16:00 \n *** No tasks scheduled ***\n" +
    			"17:00 \n *** No tasks scheduled ***\n" +
    			"18:00 \n *** No tasks scheduled ***\n" +
    			"19:00 \n *** No tasks scheduled ***\n" +
    			"20:00 \n *** No tasks scheduled ***\n" +
    			"21:00 \n *** No tasks scheduled ***\n" +
    			"22:00 \n *** No tasks scheduled ***\n" +
    			"23:00 \n *** No tasks scheduled ***\n";
    	assertEquals("New Schedule did not properly initilize",schedule.getFormattedSchedule(),expectedSchedule);
    }
    // Test that each animals cage is cleaned that day
    @Test
    public void cageCleanTest() {
        ArrayList<Treatment> careNeeded = new ArrayList<>();
        Task task1 = new Task(1, 30, 3, "Grooming");
        Treatment treatment1 = new Treatment(1, 3, task1,1);

        careNeeded.add(treatment1);

        
        Coyote animal1 = new Coyote(1, "Jared", careNeeded);
        ArrayList<Animal> animals = new ArrayList<>();
        HashMap<Integer, Animal> animalMap = new HashMap<>();
        animals.add(animal1);
        animalMap.put(animal1.getAnimalID(), animal1);
        Schedule schedule = new Schedule(animals, animalMap);
        boolean testContains = false;
        if (schedule.getFormattedSchedule().contains("Cleaning Jared's cage")) {
        	testContains = true;
        }
        assertTrue("Daily cage cleaning not implemented",testContains);

    }
    // Testing if feeding time is initialized when animals added to schedule
    @Test
    public void feedingTimeInitialized() {
        ArrayList<Treatment> careNeeded = new ArrayList<>();
        Task task1 = new Task(1, 30, 3, "Grooming");
        Treatment treatment1 = new Treatment(1, 3, task1,1);

        careNeeded.add(treatment1);

        
        Coyote animal1 = new Coyote(1, "Jared", careNeeded);
        ArrayList<Animal> animals = new ArrayList<>();
        HashMap<Integer, Animal> animalMap = new HashMap<>();
        animals.add(animal1);
        animalMap.put(animal1.getAnimalID(), animal1);
        Schedule schedule = new Schedule(animals, animalMap);
        boolean testContains = false;
        if (schedule.getFormattedSchedule().contains("feeding 1 coyotes")) {
        	testContains = true;
        }
        assertTrue("Feeding time not initialized",testContains);
    }
    // test if the database if properly read
    @Test
    public void dbRead() {
        String expected = "Coyote\n" +
                "Coyote\n" +
                "Coyote\n" +
                "Coyote\n" +
                "Coyote\n" +
                "Orphan\n" +
                "Fox\n" +
                "Porcupine\n" +
                "Porcupine\n" +
                "Porcupine\n" +
                "Porcupine\n" +
                "Porcupine\n" +
                "Coyote\n" +
                "Coyote\n" +
                "Coyote";
    	Main main = new Main();
    	main.main(null);
        StringBuilder actual = new StringBuilder();
        
        for (int i = 0; i < main.getAnimals().size(); i++) {
            actual.append(main.getAnimals().get(i).getType());
            if (i < main.getAnimals().size() - 1) {
                actual.append("\n");
            }
        }
        // Compare the expected and actual output
        assertEquals("Expected and actual outputs do not match. Expected: [" + expected + "], Actual: [" + actual.toString() + "]", expected, actual.toString());
    }
}