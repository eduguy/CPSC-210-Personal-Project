package model;

import java.util.ArrayList;

//Gym is a list of all the climbs
public class Gym {
    Wall ShowWall = new Wall("Show Wall");
    Wall Ship = new Wall("Ship");
    Wall Cave = new Wall("Cave");


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
            for (Problem p : w.getProblemList()) {
                if (p.getGrade() == i) {
                    record += ("Color: " + p.getColor() + " Wall: " + w.getName() + "\n");
                }
            }
        }
        return record;
    }

    public String getAllClimbsInOrderOfDifficulty(){
        return getAllClimbsOfDifficulty(1) + getAllClimbsOfDifficulty(2) + getAllClimbsOfDifficulty(3)
                + getAllClimbsOfDifficulty(4) + getAllClimbsOfDifficulty(5) + getAllClimbsOfDifficulty(6);
    }


    public void addProblem(Problem p, Wall wall) {

        wall.addProblem(p);

//        for (Wall w : wallList) {
//            if (w.getName().equals(wall)) {
//                w.addProblem(p);
//                return;
//            }
//        }
//
//        System.out.println("Failed because wall does not exist");
    }

    public void removeProblem(Problem p, Wall wall) {
        wall.removeProblem(p);
//        for (Wall w : wallList) {
//
//            if (w.getName().equals(wall)) {
//                w.removeProblem(p);
//                return;
//            }
//        }
//        System.out.println("Failed because wall does not exist");
    }

    //EFFECTS: return the wall (String) that a specific problem exists in
//    public String findProblem(Problem p) {
//        for (Wall w : wallList) {
//            if (w.containsProblem(p)) {
//                return w.getName();
//            }
//        }
//        return "Problem does not exist";
//    }

    public Wall getShowWall(){
        return ShowWall;
    }

    public Wall getCave() {
        return Cave;
    }



}
