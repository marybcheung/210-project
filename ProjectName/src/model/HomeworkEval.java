package model;

import java.util.Objects;

public class HomeworkEval extends Evaluation {
    private String name;

    //MODIFIES: this
    //EFFECTS: constructs a HomeworkEval and sets name and outOf to parameter values
    public HomeworkEval(String name, Integer outOf, Student student) {
        super(outOf, student);
        this.name = name;
    }


    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    @Override
    //EFFECTS: if the student got below 50% on their assignment, return a statement saying they struggled,
    //         otherwise return an empty string
    public String studentNeeds(){
        if (this.calculateGrade() < 50.00) {
            return " struggled with " + name + ", and";
        } else{
            return "";
        }
    }

    public void setStudent(Student student) {
        if (!this.student.equals(student)){
            this.student = student;
            student.addHomeworkEval(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomeworkEval)) return false;
        if (!super.equals(o)) return false;
        HomeworkEval that = (HomeworkEval) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }
}
