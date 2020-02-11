package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//creates a user, a user can add climbs to their wishlist or completed list
public class User {

    ArrayList<Problem> toDo;
    ArrayList<Problem> completed;

    public User (){
        toDo = new ArrayList<Problem>();
        completed = new ArrayList<Problem>();
    }

    public void addProblemTodo(Problem p, String location) {
        ArrayList<Problem> x;
        if (location.equals("todo")){
            x = toDo;}
        else {
            x = completed;
        }

        x.add(p);
    }

    public void removeProblemTodo(Problem p, String location) {
        ArrayList<Problem> x;
        if (location.equals("todo")){
            x = toDo;}
        else {
            x = completed;
        }
        x.remove(p);
    }

    public String getProblems(String location) {
        ArrayList<Problem> x;
        if (location.equals("todo")){
            x = toDo;}
        else {
            x = completed;
        }
        String s = "";
        for (Problem p : x) {
            s += ("Color: " + p.getColor() + " Grade: " + p.getGrade() + " Wall: " + p.getWall() + "\n");
        }
        return s;
    }



    public List<Problem> getProblemList(String location) {
        ArrayList<Problem> x;
        if (location.equals("todo")){
            x = toDo;}
        else {
            x = completed;
        }

        return x;
    }

    public boolean containsProblem(Problem p, String location) {
        ArrayList<Problem> x;
        if (location.equals("todo")){
            x = toDo;}
        else {
            x = completed;
        }
        return x.contains(p);
    }
}
