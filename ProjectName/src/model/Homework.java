package model;

public class Homework {
    private String name;
    private double outOf = 1;
    private double earned;
    private double grade;

    //MODIFIES: this
    //EFFECTS: constructs a Homework and sets name and outOf to parameter values
    public Homework(String name, Integer outOf) {
        this.name = name;
        this.outOf = outOf;
    }

    //REQUIRES: earned >= 0 && earned <= outOf
    //MODIFIES: this
    //EFFECTS: sets earned to parameter value
    public void setEarned(double earned) {
        this.earned = earned;
    }

    //returns earned
    public double getEarned() {
        return earned;
    }

    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    //REQUIRES: outOf != 0
    //MODIFIES: this
    //EFFECTS: calculates and sets the mark a student received out of 100.00
    public void calculateGrade(){
        this.grade = earned/outOf * 100.00;
    }

    //EFFECTS: returns grade
    public double getGrade() {
        return grade;
    }
}
