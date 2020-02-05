package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymTest {
    Gym gym;
    @BeforeEach
    public void setUp(){
        gym = new Gym();
    }

    @Test
    public void testGetAllProblems(){
        gym.addProblem(new Problem("Red", 5, "Show Wall"));


        assertEquals("Color: Red Grade: 5 Wall: Show Wall \n", gym.getAllProblems());
        assertEquals(null, gym.getClimbsOfDifficulty(4));

    }

}
