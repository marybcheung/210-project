package model;

import interfaces.Observer;

import java.util.*;

public class Student implements Observer {
    private String fName;
    private String lName;
    private ArrayList<HomeworkEval> listOfHomeWork = new ArrayList<>();
    private AttendanceEval attendanceEval = null;

    //MODIFIES: this
    //EFFECTS: constructs a Student and sets fName and lName to parameter values
    public Student(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
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

    //REQUIRES: given a is not null
    //MODIFIES: this
    //EFFECTS: if a is not already attendanceEval, sets a to attendanceEval
    public void setAttendanceEval(AttendanceEval a) {
        if (!a.equals(attendanceEval)){
            attendanceEval = a;
            a.setStudent(this);
        }
    }

    //EFFECTS: returns attendanceEval
    public AttendanceEval getAttendanceEval(){
        return attendanceEval;
    }

    //REQUIRES: given h is not null
    //MODIFIES: this
    //EFFECTS: if h is not in the listOfHomework, adds h to listOfHomework
    public void addHomeworkEval(HomeworkEval h){
        if (!listOfHomeWork.contains(h)){
            listOfHomeWork.add(h);
            h.setStudent(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(fName, student.fName) &&
                Objects.equals(lName, student.lName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fName, lName);
    }

    @Override
    public void update(String name, Integer outOf) {
        System.out.println(fName + " " + lName + " has been notified about new assignment: " + name +" ["+outOf+"]");
    }
}
