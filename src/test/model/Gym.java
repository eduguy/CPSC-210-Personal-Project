package model;

import java.util.ArrayList;

//Gym is a list of all the climbs
public class Gym {
    Wall ShowWall = new Wall("Showwall");
    Wall Ship = new Wall("ship");
    Wall Cave = new Wall("cave");
    ArrayList<Problem> b;
    ArrayList<Problem> c;

    ArrayList<Wall> wallList;


    public Gym() {
        wallList = new ArrayList<Wall>();
        wallList.add(ShowWall);
        wallList.add(Ship);
        wallList.add(Cave);

    }

    public String getAllProblems() {
        String record = "";
        for (Wall w : wallList) {
            record += w.getProblems();
        }
        return record;
    }

    public String getProblemsOnWall(Wall w) {
        return w.getProblems();
    }

    public String getAllClimbsOfDifficulty(int i) {
        String record = "";
        for (Wall w : wallList) {
            for (Problem p : w.getProblemList()){
                if (p.getGrade() == i){
                    record += ("Color: " + p.getColor() + " Wall: " + p.getWall() + "\n");
                }
            }
        }
        return record;
    }

   /* public void addProblem(Problem p) {
        problemList.add(p);
    }

    public void removeProblem(Problem p) {
        problemList.remove(p);
    }
*/
}
