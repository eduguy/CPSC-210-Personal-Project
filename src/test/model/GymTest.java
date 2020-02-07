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
        gym.addProblem(new Problem("Black", 4), "Cave");
        gym.addProblem(new Problem("Red", 4), "Show Wall");
        gym.addProblem(new Problem("Blue", 4), "Ship");
        //should fail
        assertEquals("abc", gym.getAllProblems());



    }

}
