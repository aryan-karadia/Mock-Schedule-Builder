package edu.ucalgary.oop;

import java.sql.*;
import java.util.*;


public class Main {
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
                        // to set activeHours to something
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
                    System.out.println("Task ID: " + task.getID() + " | Description: " + task.getDescription());
                }

                System.out.println();
            }
            Schedule schedule = new Schedule(animals, animalMap);

            //System.out.println(schedule.getFormattedSchedule());
            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
