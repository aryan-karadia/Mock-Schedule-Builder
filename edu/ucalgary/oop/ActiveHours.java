/**
 * This code defines the ActiveHours enum and has 3 constants, NOCTURNAL, 
 * DIURNAL, and CREPUSCULAR.
 * The code starts by importing the "ArrayList" class from java.util.
 * The code then defines the "ActiveHours" enum and has 3 constants, NOCTURNAL, 
 * DIURNAL, and CREPUSCULAR.
 * The "NOCTURNAL" constant has a method named "feedingHours" that returns an 
 * ArrayList that contains the numbers 0, 1, and 2.
 * The "DIURNAL" constant has a method named "feedingHours" that returns an 
 * ArrayList that contains the numbers 8, 9, and 10.
 * The "CREPUSCULAR" constant has a method named "feedingHours" that returns an 
 * ArrayList that contains the numbers 19, 20 and 21.
 * @author Aryan Karadia, Aditya Prasad, Thomas Mattern, Brock Tomlinson
 * @version 1.0
 * @since 1.0
 */

 package edu.ucalgary.oop;

 import java.util.ArrayList;
 
 public enum ActiveHours {
     NOCTURNAL {
         public ArrayList<Integer> feedingHours() {
             ArrayList<Integer> hours = new ArrayList<>();
             hours.add(0);
             hours.add(1);
             hours.add(2);
             return hours;
         }
     },
     DIURNAL {
         public ArrayList<Integer> feedingHours() {
             ArrayList<Integer> hours = new ArrayList<>();
             hours.add(8);
             hours.add(9);
             hours.add(10);
             return hours;
         }
     },
     CREPUSCULAR {
         public ArrayList<Integer> feedingHours() {
             ArrayList<Integer> hours = new ArrayList<>();
             hours.add(19);
             hours.add(20);
             hours.add(21);
             return hours;
         }
     };
 
     public abstract ArrayList<Integer> feedingHours();
 }
 