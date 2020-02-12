package model;

import java.util.ArrayList;

//Gym is a list of all the climbs
public class Gym {
    Wall showWall = new Wall("Show Wall");
    Wall bigCave = new Wall("Big Cave");
    Wall ship = new Wall("Ship");
    Wall smallCave = new Wall("Small Cave");
    Wall berg = new Wall("Berg");
    Wall slab = new Wall("Slab");


    ArrayList<Wall> wallList;


    public Gym() {
        wallList = new ArrayList<Wall>();
        wallList.add(showWall);
        wallList.add(ship);
        wallList.add(bigCave);
        wallList.add(smallCave);
        wallList.add(berg);
        wallList.add(slab);

    }

    public String toStringAllProblems() {
        String record = "";
        for (Wall w : wallList) {
            record += w.getProblems();
        }
        return record;
    }

    //public String getProblemsOnWall(Wall w) {
      //  return w.getProblems();
   // }

    public String getAllClimbsOfDifficulty(int i) {
        String record = "";
        for (Wall w : wallList) {
            for (Problem p : w.getProblemList()) {
                if (p.getGrade() == i) {
                    record += ("Color: " + p.getColor() + " Grade: " + p.getGrade() + " Wall: " + w.getName() + "\n");
                }
            }
        }
        return record;
    }

    public String getAllClimbsInOrderOfDifficulty() {
        return getAllClimbsOfDifficulty(1) + getAllClimbsOfDifficulty(2) + getAllClimbsOfDifficulty(3)
                + getAllClimbsOfDifficulty(4) + getAllClimbsOfDifficulty(5) + getAllClimbsOfDifficulty(6);
    }


    public void addProblem(Problem p, Wall wall) {

        wall.addProblem(p);
    }

    public boolean hasClimbs() {
        for (Wall w : wallList) {
            if (w.quantityProblems() > 0) {
                return true;
            }
        }
        return false;
    }

    public void removeProblem(Problem p, Wall wall) {
        wall.removeProblem(p);
    }


    public Wall getShowWall() {
        return showWall;
    }

    public Wall getBigCave() {
        return bigCave;
    }

    public Wall getShip() {
        return ship;
    }

    public Wall getSmallCave() {
        return smallCave;
    }

    public Wall getBerg() {
        return berg;
    }

    public Wall getSlab() {
        return slab;
    }


}
