package edu.ucalgary.oop;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ScheduledFuture;


public class Schedule extends JFrame implements ActionListener{

    private static HashMap<Integer, String> workingHours = new HashMap<>(24);
    private boolean backupVolunteerNeeded = false;
    private ArrayList<Animal> animals;
    private HashMap<Integer, ArrayList<Task>> tasks = new HashMap<>(24);

    public Schedule(Animal[] animals) {
        this.animals = new ArrayList<>(Arrays.asList(animals));
    }

    public Schedule() {
        // todo
    }

    public boolean getBackupNeeded() {
        return this.backupVolunteerNeeded;
    }

    public void setBackupNeeded(boolean needed) {
        this.backupVolunteerNeeded = needed;
    }

    public void scheduleFeedingAndCleaningTasks() {
        //  todo
    }

    public String getFormattedSchedule() {
        // todo
        return null;
    }

    public void addTask(int hour, Task task) {
    	//todo
    }

    public void removeTask(int hour, Task task) {
    	//todo
    }

    public void actionPerformed(ActionEvent event){
        JOptionPane.showMessageDialog(null, "This Button has been pushed.");
    }

    public static void main(String[] args) {

            EventQueue.invokeLater(() -> {
                //Creating the frame, setting the dimensions, and exiting on close.
                JFrame frame = new JFrame();
                frame.setSize(400,400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //Creating a panel first before adding the button into the panel.
                JPanel buttonsPanel = new JPanel();
                JButton button1 = new JButton("Click Here!");

                //Create a button listener,
                Schedule buttonListener = new Schedule();
                button1.addActionListener(buttonListener);

                //adding the button onto the North side of the panel.
                buttonsPanel.add(button1);
                frame.getContentPane().add(BorderLayout.NORTH, buttonsPanel);
                //turn the frame on
                frame.setVisible(true);

            });
    }
}