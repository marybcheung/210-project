package model;

import exceptions.NegativeNumeratorException;

public class AttendanceEval extends Evaluation{

    public AttendanceEval(double totalDays, Student student){
      super(totalDays, student);
        try {
            super.setEarned(totalDays);
        } catch (NegativeNumeratorException e) {
            e.printStackTrace();
        }
    }

    //REQUIRES: earned >= 1
    //MODIFIES: this
    //EFFECTS: subtracts one mark from earned
    public void deductMark() throws NegativeNumeratorException {
        if (earned <= 0){
            throw new NegativeNumeratorException();
        }
        this.earned -= 1;
    }

    @Override
    //EFFECTS: if the student has been absent five or more times, return a string saying they need help,
    //         otherwise return string saying they have a good record
    public String studentNeeds(){
        if (this.getOutOf() - this.getEarned() >= 5) {
            return " requires meaningful engagement to improve their attendance.";
        }else {
            return " has a good attendance record.";
        }
    }

    public void setStudent(Student student) {
        if (!this.student.equals(student)){
            this.student = student;
            student.setAttendanceEval(this);
        }
    }



}
