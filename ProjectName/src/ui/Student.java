package ui;

public class Student {
    String fName;
    String lName;
    String attendance = "here"; //"here" or "absent"

    public Student(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String setAttendance(Boolean b){
        if (b == false) {
            this.attendance = "absent";
        }
        return this.attendance;
    }

    public String getAttendance() {
        return attendance;
    }
}
