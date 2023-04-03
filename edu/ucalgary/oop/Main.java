package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static ResultSet results;

    public static void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
                // -change password and username
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "root", "Root");
            Statement stmt = conn.createStatement();
            results = stmt.executeQuery("SELECT * FROM ANIMALS");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
                ArrayList<Treatment> careNeeded = new ArrayList<>();
                int timeToFeed = 0;

                switch (species) {
                    case "coyote":
                    	activeHours = ActiveHours.DIURNAL;
                    	System.out.println(species);
                        Coyote coyote = new Coyote(animalID, nickname, careNeeded);
                        break;
                    case "fox":
                    	activeHours = ActiveHours.DIURNAL;
                        Fox fox = new Fox(animalID, nickname, careNeeded);
                        break;
                    case "porcupine":
                    	activeHours = ActiveHours.DIURNAL;

                        Porcupine porcupine = new Porcupine(animalID, nickname, careNeeded);
                        break;
                    case "beaver":
                    	activeHours = ActiveHours.DIURNAL;

                        Beaver beaver = new Beaver(animalID, nickname, careNeeded);
                        break;
                    case "raccoon":
                    	activeHours = ActiveHours.DIURNAL;

                        Raccoon raccoon = new Raccoon(animalID, nickname, careNeeded);

                        break;
                    default:
                    	// to set activeHours to something
                    	activeHours = ActiveHours.DIURNAL;

                    	Orphan orphan = new Orphan(animalID, nickname, activeHours, careNeeded, timeToFeed, timeToFeed, timeToFeed);
                        System.out.println("Unknown animal species: " + species);
                }
            }

            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
