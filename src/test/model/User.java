package model;

import java.util.ArrayList;

//creates a user, a user can add climbs to their wishlist or completed list
public class User extends Methods{

    ArrayList<Problem> toDo;
    ArrayList<Problem> completed;

    public User (){
        toDo = new ArrayList<Problem>();
        completed = new ArrayList<Problem>();
    }
}
