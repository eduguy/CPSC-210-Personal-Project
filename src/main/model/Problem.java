package model;

import persistence.Saveable;

import java.io.PrintWriter;

//Represents a single boulder problem having a color and grade
public class Problem implements Saveable {

    String color;
    int grade;
    String wall;

    //REQUIRES: grade is integer in [1,6]
    //EFFECTS: color of climb is set to color, grade of climb is set to grade
    public Problem(String color, int grade) {
        this.color = color;
        this.grade = grade;
    }

    public Problem(String color, int grade, String wall) {
        this.color = color;
        this.grade = grade;
        this.wall = wall;
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

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(color);
        printWriter.print(",");
        printWriter.print(grade);
        printWriter.print(",");
        printWriter.println(wall);
    }
}
