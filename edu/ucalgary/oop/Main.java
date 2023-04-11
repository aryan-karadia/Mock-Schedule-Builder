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
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The Main class is responsible for connecting to the EWR database, retrieving animal and task data,
 * creating Animal and Task objects, and ultimately creating a schedule for the animals.
 * @author Thomas Mattern, Aryan Karadia, Aditya Prasad, Brock Tomlinson
 * @version 1.6
 * @since 1.0
 */

public class Main extends JFrame {
    /**
     * The getDatabaseInfo method connects to the EWR database, retrieves animal and task data, creates Animal and Task
     * objects, and ultimately creates a schedule for the animals.
     *
     */

    public static Schedule getDatabaseInfo() {
        Schedule schedule = null;
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
                    System.out.println("Task ID: " + task.getID() + " | Description: " + task.getDESCRIPTION() + " | Time: " + treatment2.getStartTime());
                }

                System.out.println();
            }
            try {
                schedule = new Schedule(animals, animalMap);
            } catch (Exception e) {
                System.err.println("Error creating schedule: " + e.getMessage());
                System.exit(1);
                e.printStackTrace();
            }
            try {
                schedule.createScheduleFile();
            } catch (Exception e) {
                System.err.println("Error creating schedule file: " + e.getMessage());
                System.exit(1);
            }
            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    /**
     * Method to show GUI when backup volunteer is needed
     */
    public void backupVolunteerNeededGUI(Schedule schedule) {
        StringBuilder backupNeededText = new StringBuilder();
        for (int hour = 0; hour < 24; hour++) {
            if (schedule.getBackupNeeded(hour)) {
                backupNeededText.append("Backup volunteer needed at ").append(hour).append(":00\n");
            }
        }
        if (backupNeededText.length() > 0) {
            JOptionPane pane = new JOptionPane(backupNeededText.toString(), JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{ "Confirm" });
            JDialog dialog = pane.createDialog("Backup Volunteer Needed");
            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            dialog.setVisible(true);
            Object selectedValue = pane.getValue();
            if (selectedValue != null && selectedValue.equals("Confirm")) {
                dialog.dispose();
            }
        }
    }


    /**
     * Method to show GUI there isn't enough time in a task, so we move it using the gui, updates the schedule and the database
     */
    public static void notEnoughTimeGUI(int startTime, Treatment treatment, HashMap<Integer, Integer> availableMinutes, HashMap<Integer, Integer> backupAvailableMinutes, HashMap<Integer, ArrayList<Treatment>> tasks, Animal animal) {
        JFrame errorFrame = new JFrame();
        errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String message = "Not enough time left in the hour " + treatment.getStartTime() + " to do treatment: " + treatment.getTask().getDESCRIPTION() + "." +
                "\nPlease enter a new start time (0-23) for this task:";
        int newStartTime = -1;
        while (true) {
            String newStartTimeStr = JOptionPane.showInputDialog(errorFrame, message, "Error, Need to Move Task", JOptionPane.ERROR_MESSAGE);
            // convert the new start time string to integer
            try {
                newStartTime = Integer.parseInt(newStartTimeStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(errorFrame, "Invalid input. Please enter an integer from 0 to 23.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;  // ask again for input
            }
            // check if the new start time is valid
            if (newStartTime < 0 || newStartTime > 23) {
                JOptionPane.showMessageDialog(errorFrame, "Invalid input. Please enter an integer from 0 to 23.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;  // ask again for input
            }
            // check if the new start time has enough available minutes
            if ((availableMinutes.get(newStartTime) + backupAvailableMinutes.get(newStartTime)) - treatment.getTask().getDURATION() < 0) {
                JOptionPane.showMessageDialog(errorFrame, "The new start time: "+ newStartTimeStr + ", does not have enough available minutes to do this task: " + treatment.getTask().getDESCRIPTION() + ". Please assign a different time.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;  // ask again for input
            }
            // if we get here, the input is valid, so break out of the loop
            break;
        }
        // update the task start time and available minutes accordingly
        if ((availableMinutes.get(newStartTime) - treatment.getTask().getDURATION() < 0)) {
            tasks.get(newStartTime).add(treatment);
            treatment.setMinutesRemaining(backupAvailableMinutes.get(newStartTime) - treatment.getTask().getDURATION());
            backupAvailableMinutes.put(newStartTime, backupAvailableMinutes.get(newStartTime) - treatment.getTask().getDURATION());
        } else {
            tasks.get(newStartTime).add(treatment);
            treatment.setMinutesRemaining(availableMinutes.get(newStartTime) - treatment.getTask().getDURATION());
            availableMinutes.put(newStartTime, availableMinutes.get(newStartTime) - treatment.getTask().getDURATION());
        }
        // remove the task from its original time slot
        tasks.get(startTime).remove(treatment);
        // close the error frame
        errorFrame.dispose();



        try {
            // Create a connection to the EWR database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");

            // Create a PreparedStatement to update the treatment table
            PreparedStatement statement = connection.prepareStatement("UPDATE TREATMENTS SET StartHour = ? WHERE AnimalID = ? AND TaskID = ?");

            // Set the parameters of the PreparedStatement to the updated values
            statement.setString(1, String.valueOf(newStartTime));
            statement.setInt(2, animal.getAnimalID());
            statement.setInt(3, treatment.getTaskID());

            // Execute the SQL query to update the treatment table
            statement.executeUpdate();

            // Close the statement and the connection
            statement.close();
            connection.close();

            // Display a success message to the user
            JOptionPane.showMessageDialog(null, "The start time for " + animal.getName() + "'s " + treatment.getTask().getDESCRIPTION() + " task has been updated to " + newStartTime + ".", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            // Display an error message to the user
            JOptionPane.showMessageDialog(null, "An error occurred while updating the database. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        getDatabaseInfo();
    }
}