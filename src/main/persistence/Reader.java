package persistence;

import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

//CODE USED FROM TELLERAPP
// A reader that can read Gym data from a file
public class Reader {

    //EFFECTS: return a list of Problems from a file, with their wall information
    public static List<Problem> readGym(File info) throws IOException {
        List<String> content = readInfo(info);
        return parseData(content);
    }

    //EFFECTS: read content of file and return as list of strings, each string
    //         is data for one climb
    public static List<String> readInfo(File info) throws IOException {
        return Files.readAllLines(info.toPath());
    }

    //EFFECTS: return a list of problems from the list of strings
    public static List<Problem> parseData(List<String> data) {
        List<Problem> problems = new ArrayList<>();

        for (String lineInfo : data) {

            problems.add(parseProblem(splitLine(lineInfo)));

        }
        return problems;
    }

    public static ArrayList<String> splitLine(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //EFFECTS: create a problem out of string information
    public static Problem parseProblem(List<String> info) {
        String name = info.get(0);
        int grade = Integer.parseInt(info.get(1));
        String wall = info.get(2);
        return new Problem(name, grade, wall);

    }


}
