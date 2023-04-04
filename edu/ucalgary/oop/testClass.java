package edu.ucalgary.oop;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class testClass {

	@Test
	public void testInheritance() {
		ArrayList<Treatment> careNeeded = new ArrayList<>();
        Coyote animal1 = new Coyote(1, "Jared", careNeeded);

        // Test if the animal object is created and coyote can access method
        assertEquals(1, animal1.getAnimalID());
        assertEquals("Coyote", animal1.getType());
        assertEquals("Jared", animal1.getName());
        assertEquals(ActiveHours.CREPUSCULAR, animal1.getActiveHours());
        assertEquals(careNeeded, animal1.getCareNeeded());
        assertEquals(5, animal1.getTimeToFeed());
        assertEquals(10, animal1.FOODPREPTIME);
        assertEquals(5, animal1.timeToClean);
	}
    @Test
    public void taskInheritanceRelationship() {
        Task task1 = new Task(1, 5, 3, "Grooming");
        Treatment treatment1 = new Treatment(1, 3, task1);

        // Test if the treatment object is created with the correct properties
        assertEquals(1, treatment1.getTaskID());
        assertEquals(3, treatment1.getStartTime());
        assertNotNull(treatment1.getTask());

        // Test if the task object is assigned to the treatment object correctly
        assertEquals(task1.getID(), treatment1.getTask().getID());
        assertEquals(task1.getDURATION(), treatment1.getTask().getDURATION());
        assertEquals(task1.getTimeWindow(), treatment1.getTask().getTimeWindow());
        assertEquals(task1.getDescription(), treatment1.getTask().getDescription());
    }

}
