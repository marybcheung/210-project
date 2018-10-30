package model;

import exceptions.NegativeNumeratorException;

import java.util.Objects;

public abstract class Evaluation {
    protected double earned;
    private double outOf;
    private final double ONE_HUNDRED = 100.00;
    protected Student student;

    public Evaluation(double outOf, Student student){
        this.outOf = outOf;
        this.student = student;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evaluation)) return false;
        Evaluation that = (Evaluation) o;
        return Double.compare(that.earned, earned) == 0 &&
                Double.compare(that.outOf, outOf) == 0 &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {

        return Objects.hash(earned, outOf, student);
    }
}
