package model;

import java.util.ArrayList;
import java.util.List;

//represents a wall of the gym
public class Wall extends Methods {
    ArrayList<Problem> problemList;
    String name;

    public Wall(String name) {
        problemList = new ArrayList<Problem>();
        this.name = name;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void addProblem(Problem p) {
//        problemList.add(p);
//    }
//
//    public void removeProblem(Problem p) {
//        problemList.remove(p);
//    }
//
//    public String getProblems() {
//        String s = "";
//        for (Problem p : problemList) {
//            s += ("Color: " + p.getColor() + " Grade: " + p.getGrade() + " Wall: " + this.name + "\n");
//        }
//        return s;
//    }
//
//    public int quantityProblems() {
//        return problemList.size();
//    }
//
//    public List<Problem> getProblemList() {
//        return problemList;
//    }
//
//    public boolean containsProblem(Problem p) {
//        return problemList.contains(p);
//    }
}
