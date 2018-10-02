package interfaces.ForLater;

public interface MarkDeductible {
    //REQUIRES: numerator >= 0 && numerator <= denominator
    //MODIFIES: this
    //EFFECTS: deducts 1 from the mark and returns the mark
    public double deductMark();
}
