package model;

public abstract class Evaluation {
    protected double earned;
    private double outOf;
    private final double ONE_HUNDRED = 100.00;

    public Evaluation(double outOf){
        this.outOf = outOf;
    }

    //REQUIRES: earned >= 0 && earned <= outOf
    //MODIFIES: this
    //EFFECTS: sets earned to parameter value
    public void setEarned(double earned) {
        this.earned = earned;
    }

    public double calculateGrade() {
        return Math.round((earned / outOf * ONE_HUNDRED)* ONE_HUNDRED)/ONE_HUNDRED;
    }

    //EFFECTS: returns outOf
    public double getOutOf(){
        return outOf;
    }

    //returns earned
    public double getEarned() {
        return earned;
    }

    public abstract String studentNeeds();
}
