package edu.ucalgary.oop;
import java.lang.reflect.Array;
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
}
