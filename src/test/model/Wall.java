package model;

import java.util.ArrayList;

//represents a wall of the gym
public class Wall {
    ArrayList<Problem> problemList;

    public Wall(){
        problemList = new ArrayList<Problem>();
    }

    public void addProblem(Problem p){
        problemList.add(p);
    }

    public void removeProblem(Problem p){
        problemList.remove(p);
    }

    public int quantityProblems(){
        return problemList.size();
    }
}
