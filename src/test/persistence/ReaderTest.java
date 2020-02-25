package persistence;

import model.Problem;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ReaderTest {
    Writer writer;
    @Test
    void testParseTestFile1() {
        try {
            List<Problem> trial = Reader.readGym(new File("./data/testfile.txt"));
            Problem p1 = trial.get(0);
            assertEquals(p1.getColor(), "Red");
            assertEquals(p1.getGrade(), 5);
            assertEquals(p1.getWall(), "Show Wall");
        } catch (IOException e) {
            fail("Shouldn't have thrown exception :(");
        }
    }

    @Test
    void testParseTestFile2() {
        try {
            List<Problem> trial = Reader.readGym(new File("./data/testfile2.txt"));
            Problem p1 = trial.get(0);
            assertEquals(p1.getColor(), "Red");
            assertEquals(p1.getGrade(), 5);
            assertEquals(p1.getWall(), "Show Wall");
            Problem p2 = trial.get(1);
            assertEquals(p2.getColor(), "Blue");
            assertEquals(p2.getGrade(), 3);
            assertEquals(p2.getWall(), "Big Cave");
        } catch (IOException e) {
            fail("Shouldn't have thrown exception :(");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readGym(new File("./data/dne"));
        } catch (IOException e) {
            //expected
        }

    }



}
