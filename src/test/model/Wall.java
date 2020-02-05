package model;

import java.util.ArrayList;

//represents a wall of the gym
public class Wall {
    ArrayList<Problem> problemList;

    public Wall() {
        problemList = new ArrayList<Problem>();
    }

    public void addProblem(Problem p) {
        problemList.add(p);
    }

    public void removeProblem(Problem p) {
        problemList.remove(p);
    }

    public String getProblems() {
        String s = "";
        for (Problem p : problemList) {
            s += ("Color: " + p.getColor() + " Grade: " + p.getGrade() + "\n");
        }
        return s;
    }

    public int quantityProblems() {
        return problemList.size();
    }
}
