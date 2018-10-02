package model.ForLater;

import interfaces.ForLater.Assessment;
import interfaces.ForLater.MarkDeductible;

public class Attendance implements Assessment, MarkDeductible {
    private double totalDays;
    private double attendedDays;

    public Attendance(double totalDays){
        this.totalDays = totalDays;
        this.attendedDays = totalDays;
    }

    @Override
    public double calculateGrade() {
        return Math.round((attendedDays/totalDays * 100.00)* 100.00)/100.00;
    }

    @Override
    public double deductMark() {
        attendedDays-=1;
        return calculateGrade();
    }

    public double getAttendedDays(){
        return this.attendedDays;
    }
}
