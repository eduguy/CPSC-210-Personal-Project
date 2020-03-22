package model;

import java.util.ArrayList;
import java.util.List;

//Represents a wall of the gym having a name and a list of problems
public class Wall {
    ArrayList<Problem> problemList;
    String name;

    //EFFECTS: name of wall is set to name, list of problems is initialized
    public Wall(String name) {
        problemList = new ArrayList<Problem>();
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: adds climb to this wall
    public void addProblem(Problem p) {
        problemList.add(p);
        p.setWall(this.name);
    }

    //MODIFIES: this
    //EFFECTS: removes climb from this wall
    public void removeProblem(Problem p) {
        problemList.remove(p);
        p.setWall(null);
    }

    //EFFECTS: Prints out all problems on this wall with their information
    public String getProblems() {
        String s = "";
        for (Problem p : problemList) {
            s += ("Color: " + p.getColor() + " | Grade: " + p.getGrade() + " | Wall: " + this.name + "\n");
        }
//        String s1 = ("<html>" + s.replaceAll("<", "&lt;")
//                .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
//        return s1;
        return s;
//        String s = "";
//        int i = 1;
//        for (Problem p : problemList) {
//            s += (i + ": Color: " + p.getColor() + " | Grade: " + p.getGrade() + " | Wall: " + p.getWall() + "\n");
//            i++;
//        }
//        //code used from stackoverflow:https://stackoverflow.com/questions/1090098/newline-in-jlabel
//        String s1 = ("<html>" + s.replaceAll("<", "&lt;")
//                .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
//        return s1;
    }

    public List<Problem> getProblemList() {
        return problemList;
    }

    public String getName() {
        return name;
    }


}
