package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String fName;
    private String lName;
    private ArrayList<HomeworkEval> listOfHomeWork = new ArrayList<>();
    private AttendanceEval attendanceEval;

    //MODIFIES: this
    //EFFECTS: constructs a Student and sets fName and lName to parameter values
    public Student(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
        //attendance = new AttendanceEval(StudentManager.totalDays);
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
    public List<HomeworkEval> getListOfHomework(){
        return this.listOfHomeWork;
    }

    public void setAttendanceEval(AttendanceEval attendanceEval) {
        this.attendanceEval = attendanceEval;
    }

    public AttendanceEval getAttendanceEval(){
        return attendanceEval;
    }
}
