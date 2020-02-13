package model;

//Represents a single boulder problem having a color and grade
public class Problem {

    String color;
    int grade;
    String wall;

    //REQUIRES: grade is integer in [1,6]
    //EFFECTS: color of climb is set to color, grade of climb is set to grade
    public Problem(String color, int grade) {
        this.color = color;
        this.grade = grade;
    }

    public String getColor() {
        return color;
    }

    public int getGrade() {
        return grade;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String name) {
        wall = name;
    }

}
