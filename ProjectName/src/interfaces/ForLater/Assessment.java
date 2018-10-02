package interfaces.ForLater;

public interface Assessment {
    //REQUIRES: outOf != 0
    //MODIFIES: this
    //EFFECTS: calculates and sets the mark a student received out of 100.00
    public double calculateGrade();
}
