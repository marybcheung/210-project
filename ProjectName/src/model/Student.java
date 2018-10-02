package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String fName;
    private String lName;
    private ArrayList<Homework> listOfHomeWork = new ArrayList<>();
    //private Attendance attendance; <-- may implement in StudentManager later

    //MODIFIES: this
    //EFFECTS: constructs a Student and sets fName and lName to parameter values
    public Student(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
        //attendance = new Attendance(StudentManager.totalDays);
    }

    //EFFECTS: returns the first name
    public String getfName() {
        return this.fName;
    }

    //EFFECTS: returns ths last name
    public String getlName() {
        return this.lName;
    }

    //EFFECTS: returns listOfHomework
    public List<Homework> getListOfHomework(){
        return this.listOfHomeWork;
    }
}
