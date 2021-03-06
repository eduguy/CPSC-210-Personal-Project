package model;

import exceptions.ClimbAlreadyExists;

import java.util.ArrayList;

//Represents the whole Gym, which consists of walls with climbs
public class Gym {
    private Wall showWall = new Wall("Show Wall");
    private Wall bigCave = new Wall("Big Cave");
    private Wall ship = new Wall("Ship");
    private Wall smallCave = new Wall("Small Cave");
    private Wall berg = new Wall("Berg");
    private Wall slab = new Wall("Slab");


    public ArrayList<Wall> wallList;

    //EFFECTS: Creates a new gym object
    public Gym() {
        wallList = new ArrayList<Wall>();
        wallList.add(showWall);
        wallList.add(ship);
        wallList.add(bigCave);
        wallList.add(smallCave);
        wallList.add(berg);
        wallList.add(slab);

    }

    //EFFECTS: Prints out all problems in the gym with their information
    public String toStringAllProblems() {

        String record = "";
        for (Wall w : wallList) {

            record += w.getProblems();
        }
        System.out.println(record);
        return record;
    }


    //EFFECTS: Prints out all problems in the gym with their information
    public String getAllClimbsOfDifficulty(int i) {
        String record = "";
        for (Wall w : wallList) {
            for (Problem p : w.getProblemList()) {
                if (p.getGrade() == i) {
                    record += ("Color: " + p.getColor() + " | Grade: " + p.getGrade()
                            + " | Wall: " + w.getName() + "\n");
                }
            }
        }
        return record;
    }

    //EFFECTS: Prints out all problems in the gym in order of their grades
    public String getAllClimbsInOrderOfDifficulty() {
        String result = getAllClimbsOfDifficulty(1) + getAllClimbsOfDifficulty(2) + getAllClimbsOfDifficulty(3)
                + getAllClimbsOfDifficulty(4) + getAllClimbsOfDifficulty(5) + getAllClimbsOfDifficulty(6);
//        if (result.equals("")) {
//            throw new NoClimbsExist();
//        }
        return result;
    }

    //MODIFIES: wall parameter
    //EFFECTS: adds a new problem to given wall
    public void addProblem(Problem p, Wall wall) throws ClimbAlreadyExists {
        wall.addProblem(p);
    }

    //MODIFIES: wall parameter
    //EFFECTS: removes a given problem from given wall
    public void removeProblem(Problem p, Wall wall) {
        wall.removeProblem(p);
    }

    //EFFECTS: returns true if gym has > 0 climbs, false otherwise
    public boolean hasClimbs() {
        for (Wall w : wallList) {
            if (!w.getProblemList().isEmpty()) {
                return true;
            }
        }
        return false;
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
