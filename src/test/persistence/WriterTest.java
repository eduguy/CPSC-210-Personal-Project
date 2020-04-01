package persistence;

import exceptions.ClimbAlreadyExists;
import exceptions.GradeOutOfBounds;
import model.Gym;
import model.Problem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {

    String testFile = "./data/writeTest.txt";
    Gym gym;
    Writer test;
    Problem p1;
    Problem p2;

    @BeforeEach
    void setUp() throws FileNotFoundException, UnsupportedEncodingException {
        try{
            p1 = new Problem("Red", 5);
            p2 = new Problem("Blue", 2);

        } catch (GradeOutOfBounds gradeOutOfBounds){
            fail();
        }
        gym = new Gym();
        try {
            gym.addProblem(p1, gym.getShowWall());
            gym.addProblem(p2, gym.getBigCave());
        } catch (ClimbAlreadyExists climbAlreadyExists) {
            climbAlreadyExists.printStackTrace();
            fail();
        }
        test = new Writer(new File(testFile));

    }

    @Test
    public void testWriter() {

        //attempt to save problems
        test.write(p1);
        test.write(p2);
        test.close();

        try {
            List<Problem> problems = Reader.readProblems(new File(testFile));
            Problem p1_1 = problems.get(0);
            Problem p2_1 = problems.get(1);

            assertEquals(p1_1.getColor(), "Red");
            assertEquals(p1_1.getGrade(), 5);
            assertEquals(p1_1.getWall(), "Show Wall");

            assertEquals(p2_1.getColor(), "Blue");
            assertEquals(p2_1.getGrade(), 2);
            assertEquals(p2_1.getWall(), "Big Cave");

        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception");
        }


    }
}
