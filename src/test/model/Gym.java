package model;

import java.util.ArrayList;

//Gym is a list of all the climbs
public class Gym {
    Wall ShowWall = new Wall();
    Wall Ship = new Wall();
    Wall Cave = new Wall();
    ArrayList<Problem> b;
    ArrayList<Problem> c;

    ArrayList<Problem> problemList;


    public Gym() {
        b = new ArrayList<Problem>();
        c = new ArrayList<Problem>();

        problemList = new ArrayList<Problem>();
    }

    public String getAllProblems() {
        String record = "";
        for (Problem p : problemList) {
            record += ("Color: " + p.getColor() + " Grade: " + p.getGrade() + " Wall: " + p.getWall() + "\n");
        }
        return record;
    }

    public String getProblemsOnWall(String wall) {
        String record = "";
        for (Problem p : problemList){
            if (p.getWall() == wall){
                record += ("Color: " + p.getColor() + " Grade: " + p.getGrade() + "\n");
            }
        }
        return record;
    }

    public String getClimbsOfDifficulty(int i) {
        String record = null;
        for (Problem p : problemList) {
            if (p.getGrade() == i) {
                record += ("Color: " + p.getColor() + " Wall: " + p.getWall() + "\n");
            }
        }
        return record;
    }

    public void addProblem(Problem p) {
        problemList.add(p);
    }

    public void removeProblem(Problem p) {
        problemList.remove(p);
    }

}
