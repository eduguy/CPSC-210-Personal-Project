package model;

import java.util.ArrayList;

//creates a user who can add climbs to their todolist
public class User {
    String name;
    ArrayList<Problem> toDoList;

    public User(String name) {
        this.name = name;
        toDoList = new ArrayList<>();
    }

    public void addClimb(Problem p) {
        toDoList.add(p);
    }

    public void removeClimb(Problem p) {
        try {
            toDoList.remove(p);
        } catch (Exception e) {
            System.out.println("Climb isn't here");
        }
    }

    public void rateClimb(int rating){

    }
}
