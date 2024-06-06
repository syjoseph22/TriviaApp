package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        String fileLocation = "C:\\Users\\samue\\IntelliJProjects\\TriviaApp\\src\\questions1.txt";
        // Set up the question manager.
        File file = new File(fileLocation);
        QuestionManager manager = new QuestionManager(file);

        // Scanner setup
        Scanner input = new Scanner(System.in);

        // Create the game object
        TriviaGame game = new TriviaGame(manager, input);

        char menuOption;
        // Main game loop
        do {
            // Display the menu
            System.out.println("Welcome to Trivia! Please choose a menu option.");
            System.out.println("A. Play");
            System.out.println("B. Modify questions");
            System.out.println("C. Options");
            System.out.println("D. Quit");
            menuOption = input.next().charAt(0);

            switch (menuOption) {
                case 'A':
                 case 'a':
                     game.play();
                    break;
                case 'B':
                 case 'b':
                     game.modify(fileLocation);
                    break;
                case 'C':
                case 'c':
                    game.options();
                    break;
                case 'D':
                case 'd':
                    System.out.println("Thanks for playing! Goodbye.");
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        } while (menuOption != 'D');
    }





}