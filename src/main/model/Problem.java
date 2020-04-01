package model;

import exceptions.GradeOutOfBounds;
import persistence.Saveable;

import java.io.PrintWriter;

//Represents a single boulder problem having a color and grade
public class Problem implements Saveable {

    String color;
    int grade;
    String wall;

    //EFFECTS: color of climb is set to color, grade of climb is set to grade, throws exception if grade is not in
    // [1,6]
    public Problem(String color, int grade) throws GradeOutOfBounds {
        if (grade < 1 || grade > 6) {
            throw new GradeOutOfBounds();
        }
        this.color = color;
        this.grade = grade;
    }


    //EFFECTS: creates a new problem, throws exception if grade is not in bounds
    //SHOULD ONLY BE USED FOR LOADING DATA
    public Problem(String color, int grade, String wall) throws GradeOutOfBounds {
        if (grade < 1 || grade > 6) {
            throw new GradeOutOfBounds();
        }
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
