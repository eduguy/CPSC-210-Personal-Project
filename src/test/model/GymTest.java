package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GymTest {
    Gym gym;

    @BeforeEach
    public void setUp() {
        gym = new Gym();
    }

    @Test
    public void testGetAllProblems() {
        gym.addProblem(new Problem("Black", 4), gym.getShowWall());
        gym.addProblem(new Problem("Red", 4), gym.getBigCave());
        gym.addProblem(new Problem("Blue", 4), gym.getShowWall());
        //should fail
        assertEquals("Color: Black Grade: 4 Wall: Show Wall\n" +
                "Color: Blue Grade: 4 Wall: Show Wall\n" +
                "Color: Red Grade: 4 Wall: Big Cave\n", gym.toStringAllProblems());


    }

    @Test
    public void testGetClimbsOfDifficulty() {
        gym.addProblem(new Problem("Black", 4), gym.getShowWall());
        gym.addProblem(new Problem("Red", 4), gym.getBigCave());
        gym.addProblem(new Problem("Blue", 3), gym.getShowWall());
        assertEquals("Color: Black Grade: 4 Wall: Show Wall\n" +
                "Color: Red Grade: 4 Wall: Big Cave\n", gym.getAllClimbsOfDifficulty(4));
        assertEquals("Color: Blue Grade: 3 Wall: Show Wall\n", gym.getAllClimbsOfDifficulty(3));

    }

    @Test
    public void testGetAllClimbsInOrderOfDifficulty() {
        gym.addProblem(new Problem("Black", 4), gym.getShowWall());
        gym.addProblem(new Problem("Red", 3), gym.getBigCave());
        gym.addProblem(new Problem("Blue", 1), gym.getShowWall());
        assertEquals("Color: Blue Grade: 1 Wall: Show Wall\n" +
                "Color: Red Grade: 3 Wall: Big Cave\n" +
                "Color: Black Grade: 4 Wall: Show Wall\n", gym.getAllClimbsInOrderOfDifficulty());
    }


    @Test
    public void testRemoveProblem() {
        Problem p = new Problem ("red", 2);
        gym.addProblem(p, gym.getBigCave());
        assertTrue(gym.hasClimbs());
        gym.removeProblem(p, gym.getBigCave());
        assertFalse(gym.hasClimbs());
    }

    @Test
    public void testHasClimbs() {
        assertFalse(gym.hasClimbs());
        gym.addProblem(new Problem("red", 2), gym.getShowWall());
        assertTrue(gym.hasClimbs());
    }
//    @Test
//    public void testProblemsOnWall(){
//        gym.addProblem(new Problem("Black", 4), gym.getShowWall());
//        gym.addProblem(new Problem("Red", 4), gym.getBigCave());
//        gym.addProblem(new Problem("Blue", 3), gym.getShowWall());
//        assertEquals("a", gym.getProblemsOnWall(gym.getShowWall()));
//
//    }

}
