package ui;

public class Student {

    public String name;
    private int grade;
    private int quizTotal;
    private int percent;

    Student(String name, int grade, int quizTotal) {
        this.name = name;
        this.grade = grade;
        this.quizTotal = quizTotal;
    }

    public int calculateGrade() {
        this.percent = (grade * 100 / quizTotal);
        return percent;
    }
}
