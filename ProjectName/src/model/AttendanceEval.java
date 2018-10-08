package model;

public class AttendanceEval extends Evaluation{

    public AttendanceEval(double totalDays){
      super(totalDays);
      super.setEarned(totalDays);
    }

    public void deductMark() {
        this.earned -= 1;
    }

    @Override
    public String studentNeeds(){
        if (this.getOutOf() - this.getEarned() >= 5) {
            return " requires meaningful engagement to improve their attendance.";
        }else {
            return " has a good attendance record.";
        }
    }
}
