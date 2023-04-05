/*
This code manages a schedule for an animal care center.
The code starts by creating a connection to the database.
The code then creates a statement, a result set, and a result set.
The code then creates an array list, a hash map, and a hash map.
The code then enters a while loop that will run as long as there are more rows in the result set.
The code will then get the values at each column of the result set and store them in defined variables.
The code will then create an animal object and add it to the array list and the hash map.
The code then enters another while loop that will run as long as there are more rows in the result set.
The code will then get the values at each column of the result set and store them in defined variables.
The code will then create a task object and add it to the array list and the hash map.
The code then enters another while loop that will run as long as there are more rows in the result set.
The code will then get the values at each column of the result set and store them in defined variables.
The code will then create a treatment object and add it to the array list.
The code then enters another while loop that will run as long as there are more rows in the result set.
The code will then get the values at each column of the result set and store them in defined variables.
The code will then create a treatment object and add it to the array list.
The code will then add the treatment to the corresponding animal's careNeeded list.
The code then enters a for loop that will run as long as there are more animals in the array list.
The code will then print the animal ID and the animal nickname.
The code will then print the care needed for the animal.
The code will then create a schedule object and catch any errors.
The code will then create a schedule file and catch any errors.
The code will then close the connection.
*/
package edu.ucalgary.oop;

import java.sql.*;
import java.util.*;

/**
 * The Main class is responsible for connecting to the EWR database, retrieving animal and task data,
 * creating Animal and Task objects, and ultimately creating a schedule for the animals.
 */
public class Main {

    /**
     * The main method connects to the EWR database, retrieves animal and task data, creates Animal and Task
     * objects, and ultimately creates a schedule for the animals.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection connector = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");
            Statement stmt = connector.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM ANIMALS");

            ArrayList<Animal> animals = new ArrayList<>();
            HashMap<Integer, Animal> animalMap = new HashMap<>();

            while (results.next()) {
                int animalID = results.getInt("AnimalID");
                String nickname = results.getString("AnimalNickname");
                String species = results.getString("AnimalSpecies");
                ActiveHours activeHours;
                ArrayList<Treatment> careNeeded = new ArrayList<>();
                int timeToFeed = 0;

                Animal animal = null;

                switch (species) {
                    case "coyote":
                        animal = new Coyote(animalID, nickname, careNeeded);
                        break;
                    case "fox":
                        animal = new Fox(animalID, nickname, careNeeded);
                        break;
                    case "porcupine":
                        animal = new Porcupine(animalID, nickname, careNeeded);
                        break;
                    case "beaver":
                        animal = new Beaver(animalID, nickname, careNeeded);
                        break;
                    case "raccoon":
                        animal = new Raccoon(animalID, nickname, careNeeded);
                        break;
                    default:
                        // to set activeHours to something but it doesn't matter
                        activeHours = ActiveHours.DIURNAL;
                        animal = new Orphan(animalID, nickname, activeHours, careNeeded, timeToFeed, timeToFeed, timeToFeed);
                        System.out.println("Unknown animal species: " + species);
                }

                if (animal != null) {
                    animals.add(animal);
                    animalMap.put(animalID, animal);
                }
            }
            ArrayList<Task> tasks = new ArrayList<>();
            Map<Integer, Task> taskMap = new HashMap<>();
            ResultSet taskResults = stmt.executeQuery("SELECT * FROM TASKS");

            while (taskResults.next()) {
                int taskID = taskResults.getInt("TaskID");
                int duration = taskResults.getInt("Duration");
                int maxWindow = taskResults.getInt("MaxWindow");
                String description = taskResults.getString("Description");

                Task task = new Task(taskID, duration, maxWindow, description);
                tasks.add(task);
                taskMap.put(task.getID(), task);        
           }
            ArrayList<Treatment> treatments = new ArrayList<>();
            ResultSet treatmentResults = stmt.executeQuery("SELECT * FROM TREATMENTS");

            while (treatmentResults.next()) {
                int animalID = treatmentResults.getInt("AnimalID");
                int taskID = treatmentResults.getInt("TaskID");
                int startHour = treatmentResults.getInt("StartHour");

                if (animalMap.containsKey(animalID) && taskMap.containsKey(taskID)) {
                    Task task = taskMap.get(taskID);
                    Treatment treatment = new Treatment(taskID, startHour, task, animalID);
                    treatments.add(treatment);

                    // Add the treatment to the corresponding animal's careNeeded list
                    Animal animal = animalMap.get(animalID);
                    animal.getCareNeeded().add(treatment);
                    // if treatment has id of 1 then changes type of animal to orphan
                    if (treatment.getTaskID() == 1) {
                        animal.setType("Orphan");
                    }
                } else {
                    System.out.println("Invalid AnimalID or TaskID: " + animalID + ", " + taskID);
                }
            }
            for (Animal animal : animals) {
                System.out.println("Animal ID: " + animal.getAnimalID() + " | Animal Nickname: " + animal.getName());
                System.out.println("Care Needed:");

                ArrayList<Treatment> treatments1 = animal.getCareNeeded();
                for (Treatment treatment2 : treatments1) {
                    Task task = taskMap.get(treatment2.getTaskID());
                    System.out.println("Task ID: " + task.getID() + " | Description: " + task.getDescription() + " | Time: "+ treatment2.getStartTime());
                }

                System.out.println();
            }
            Schedule schedule = null;
            try {
                schedule = new Schedule(animals, animalMap);
            } catch (Exception e) {
                System.err.println("Error creating schedule: " + e.getMessage());
                System.exit(1);
                e.printStackTrace();
            }
            try {
                schedule.createScheduleFile();
            }
            catch (Exception e) {
                System.err.println("Error creating schedule file: " + e.getMessage());
                System.exit(1);
            }
            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}