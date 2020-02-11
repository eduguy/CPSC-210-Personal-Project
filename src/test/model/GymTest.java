package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymTest {
    Gym gym;

    @BeforeEach
    public void setUp() {
        gym = new Gym();
    }

    @Test
    public void testGetAllProblems() {
        gym.addProblem(new Problem("Black", 4), gym.getShowWall());
        gym.addProblem(new Problem("Red", 4), gym.getCave());
        gym.addProblem(new Problem("Blue", 4), gym.getShowWall());
        //should fail
        assertEquals("abc", gym.getAllProblems());



    }

    @Test
    public void testGetClimbsOfDifficulty(){
        gym.addProblem(new Problem("Black", 4), gym.getShowWall());
        gym.addProblem(new Problem("Red", 4), gym.getCave());
        gym.addProblem(new Problem("Blue", 3), gym.getShowWall());
        assertEquals("a", gym.getAllClimbsOfDifficulty(4));
        assertEquals("b", gym.getAllClimbsOfDifficulty(3));

    }

    @Test
    public void testProblemsOnWall(){
        gym.addProblem(new Problem("Black", 4), gym.getShowWall());
        gym.addProblem(new Problem("Red", 4), gym.getCave());
        gym.addProblem(new Problem("Blue", 3), gym.getShowWall());
        assertEquals("a", gym.getProblemsOnWall(gym.getShowWall()));

    }

}
