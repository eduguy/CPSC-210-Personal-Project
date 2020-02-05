package model;

//Represents a single boulder problem
public class Problem {

    String color;
    int grade;
    String style;
    String wall;

    public Problem(String color, int grade, String wall) {
        this.color = color;
        this.grade = grade;

        this.wall = wall;
    }

    String getColor() {
        return color;
    }

    public int getGrade() {
        return grade;
    }

    public String getWall(){
        return wall;
    }

}
