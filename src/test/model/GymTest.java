package model;

import exceptions.ClimbAlreadyExists;
import exceptions.GradeOutOfBounds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.GenericSignatureFormatError;

import static org.junit.jupiter.api.Assertions.*;

public class GymTest {
    Gym gym;

    @BeforeEach
    public void setUp() {
        gym = new Gym();
    }

    //PHASE 4
    @Test
    public void addProblemExceptionExpected() {
        try {
            gym.addProblem(new Problem("Black", 4), gym.getShowWall());
            gym.addProblem(new Problem("Black", 4), gym.getShowWall());
            fail("Exception should be thrown");

        } catch (ClimbAlreadyExists climbAlreadyExists) {
            //expected
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            fail();
        }

    }


    @Test
    public void testGetAllProblems() {
        try {
            gym.addProblem(new Problem("Black", 4), gym.getShowWall());

            gym.addProblem(new Problem("Red", 4), gym.getBigCave());

            gym.addProblem(new Problem("Blue", 4), gym.getShowWall());

        } catch (ClimbAlreadyExists climbAlreadyExists) {
            climbAlreadyExists.printStackTrace();
            fail();
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            fail();
        }


        assertEquals("Color: Black | Grade: 4 | Wall: Show Wall\n" +
                "Color: Blue | Grade: 4 | Wall: Show Wall\n" +
                "Color: Red | Grade: 4 | Wall: Big Cave\n", gym.toStringAllProblems());


    }


    @Test
    public void testGetClimbsOfDifficulty() {
        try {
            gym.addProblem(new Problem("Black", 4), gym.getSlab());
            gym.addProblem(new Problem("Red", 4), gym.getBigCave());
            gym.addProblem(new Problem("Blue", 3), gym.getShip());
        } catch (ClimbAlreadyExists climbAlreadyExists) {
            fail();
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            fail();
        }

        assertEquals("Color: Red | Grade: 4 | Wall: Big Cave\n" +
                "Color: Black | Grade: 4 | Wall: Slab\n", gym.getAllClimbsOfDifficulty(4));
        assertEquals("Color: Blue | Grade: 3 | Wall: Ship\n", gym.getAllClimbsOfDifficulty(3));

    }

    @Test
    public void testGetAllClimbsInOrderOfDifficulty() {
        try {
            gym.addProblem(new Problem("Black", 4), gym.getShowWall());
            gym.addProblem(new Problem("Red", 3), gym.getSmallCave());
            gym.addProblem(new Problem("Blue", 1), gym.getBerg());
        } catch (ClimbAlreadyExists climbAlreadyExists) {
            fail();
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            fail();
        }

        assertEquals("Color: Blue | Grade: 1 | Wall: Berg\n" +
                "Color: Red | Grade: 3 | Wall: Small Cave\n" +
                "Color: Black | Grade: 4 | Wall: Show Wall\n", gym.getAllClimbsInOrderOfDifficulty());
    }


    @Test
    public void testRemoveProblem() {
        Problem p = null;
        try {
            p = new Problem("red", 2);
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            fail();
        }
        try {
            gym.addProblem(p, gym.getBigCave());
        } catch (ClimbAlreadyExists climbAlreadyExists) {
            fail();
        }
        assertTrue(gym.hasClimbs());
        gym.removeProblem(p, gym.getBigCave());
        assertFalse(gym.hasClimbs());
    }

    @Test
    public void testHasClimbs() {
        assertFalse(gym.hasClimbs());
        try {
            gym.addProblem(new Problem("red", 2), gym.getShowWall());
        } catch (ClimbAlreadyExists climbAlreadyExists) {
            climbAlreadyExists.printStackTrace();
            fail();
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            fail();
        }
        assertTrue(gym.hasClimbs());
    }

    @Test
    public void testGetWall() {
        Problem p = null;
        try {
            p = new Problem("Red", 2);
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            fail();
        }
        try {
            gym.addProblem(p, gym.getSmallCave());
        } catch (ClimbAlreadyExists climbAlreadyExists) {
            climbAlreadyExists.printStackTrace();
            fail();
        }
        assertEquals("Small Cave", p.getWall());
    }

    @Test
    public void addSameClimbTwice() {
        try {
            gym.addProblem(new Problem("Red", 5), gym.getShip());
            gym.addProblem(new Problem("Red", 5), gym.getShip());
            fail();
        } catch (ClimbAlreadyExists climbAlreadyExists) {
            //
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            fail();

        }
    }

    @Test
    public void addProblemException() {
        try {
            Problem p = new Problem("Red", 7);
            fail();
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            //expected
        }

        try {
            Problem p = new Problem("Red", 0, "Show Wall");
            fail();
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            //
        }

        try {
            Problem p = new Problem("Red", 7, "Show Wall");
            fail();
        } catch (GradeOutOfBounds gradeOutOfBounds){
            //
        }

        try {
            Problem p = new Problem("Red", 0);
            fail();
        } catch (GradeOutOfBounds gradeOutOfBounds){
            //
        }

        try {
            Problem p = new Problem("Red", 2, "Small Cave");
        } catch (GradeOutOfBounds gradeOutOfBounds) {
            fail();
        }
    }


}
