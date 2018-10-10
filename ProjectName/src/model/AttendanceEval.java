package model;

public class AttendanceEval extends Evaluation{

    public AttendanceEval(double totalDays){
      super(totalDays);
      super.setEarned(totalDays);
    }

    //MODIFIES: this
    //EFFECTS: subtracts one mark from earned
    public void deductMark() {
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
}
