package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static ResultSet results;
    public static void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "root", "Root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Treatment> getAnimalTreatment(int animalID) {
        ArrayList<Treatment> animalTreatments = new ArrayList<>();
        try {
            Connection connector = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "root", "Root");
            Statement stmt = connector.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM TREATMENTS WHERE AnimalID = " + animalID);

            while (results.next()) {
                int taskID = results.getInt("TaskID");
                int startTime = results.getInt("StartHour");
                Treatment treatment = new Treatment(taskID, startTime);
                animalTreatments.add(treatment);
            }

            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animalTreatments;
    }
    public static void main(String[] args) {
        createConnection();
        try {
            Connection connector = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "root", "Root");
            Statement stmt = connector.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM ANIMALS");
            ArrayList<Animal> animals = new ArrayList<>();

            while (results.next()) {
                int animalID = results.getInt("AnimalID");
                String nickname = results.getString("AnimalNickname");
                String species = results.getString("AnimalSpecies");
                ActiveHours activeHours;
                ArrayList<Treatment> careNeeded = getAnimalTreatment(animalID);
                int timeToFeed = 0;

                switch (species) {
                    case "coyote":
                        Coyote coyote = new Coyote(animalID, nickname, careNeeded);
                        animals.add(coyote);
                        break;
                    case "fox":
                        Fox fox = new Fox(animalID, nickname, careNeeded);
                        animals.add(fox);
                        break;
                    case "porcupine":
                        Porcupine porcupine = new Porcupine(animalID, nickname, careNeeded);
                        animals.add(porcupine);
                        break;
                    case "beaver":
                        Beaver beaver = new Beaver(animalID, nickname, careNeeded);
                        animals.add(beaver);
                        break;
                    case "raccoon":
                        Raccoon raccoon = new Raccoon(animalID, nickname, careNeeded);
                        animals.add(raccoon);

                        break;
                    default:
                    	// to set activeHours to something
                    	activeHours = ActiveHours.DIURNAL;
                    	Orphan orphan = new Orphan(animalID, nickname, activeHours, careNeeded, timeToFeed, timeToFeed, timeToFeed);
                    	animals.add(orphan);
                }
            }

            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
