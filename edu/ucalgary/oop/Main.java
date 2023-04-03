package edu.ucalgary.oop;

import java.sql.*;
        

public class Main {


    private Connection dbConnect;
    private ResultSet results;

    public void createConnection(){
            
        try{
            //                                                                      USERNAME, PASSWORD
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/pets", "oop", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
