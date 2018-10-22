package model;

import exceptions.NegativeNumeratorException;

public abstract class Evaluation {
    protected double earned;
    private double outOf;
    private final double ONE_HUNDRED = 100.00;

    public Evaluation(double outOf){
        this.outOf = outOf;
    }

    //MODIFIES: this
    //EFFECTS: sets earned to parameter value
    public void setEarned(double earned) throws NegativeNumeratorException {
        if (earned < 0){
            throw new NegativeNumeratorException();
        }
        this.earned = earned;
    }

    //REQUIRES: outOf != 0
    //EFFECTS: returns grade percentage
    public double calculateGrade() {
        return Math.round((earned / outOf * ONE_HUNDRED)* ONE_HUNDRED)/ONE_HUNDRED;
    }

    //EFFECTS: returns outOf
    public double getOutOf(){
        return outOf;
    }

    //EFFECTS: returns earned
    public double getEarned() {
        return earned;
    }

    //EFFECTS: returns a string describing what a student needs
    public abstract String studentNeeds();
}
