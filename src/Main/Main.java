package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\samue\\IntelliJProjects\\TriviaApp\\src\\questions1.txt");
        QuestionManager man = new QuestionManager(file);
        man.getAllQuestions().forEach(System.out::println);
    }
}