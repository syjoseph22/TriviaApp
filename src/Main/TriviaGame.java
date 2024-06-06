package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TriviaGame {
    private static final int EASY_DIFFICULTY_CAP = 3;
    private static final int MEDIUM_DIFFICULTY_CAP = 7;
    private static final int HARD_DIFFICULTY_CAP = 10;

    QuestionManager manager;
    Scanner input;

    private boolean askDifficulty = true;
    private boolean timedPlay = true;
    public TriviaGame(QuestionManager manager, Scanner input) {
        this.manager = manager;
        this.input = input;
    }

    public void play() {
        if (askDifficulty) {
            // Get the desired difficulty setting
            System.out.println("Select the difficulty level:");
            System.out.println("A. Easy\nB. Medium\nC. Hard");
            boolean valid = false;
            while (!valid) {
                switch (input.next().toUpperCase().charAt(0)) {
                    case 'A':
                        manager.setMaxDifficulty(EASY_DIFFICULTY_CAP);
                        valid = true;
                        break;
                    case 'B':
                        manager.setMaxDifficulty(MEDIUM_DIFFICULTY_CAP);
                        valid = true;
                        break;
                    case 'C':
                        manager.setMaxDifficulty(HARD_DIFFICULTY_CAP);
                        valid = true;
                        break;
                    default:
                        System.out.println("Invalid entry");
                }
            }
        } else {
            // Default difficulty is max
            manager.setMaxDifficulty(HARD_DIFFICULTY_CAP);
        }

        // Ask 10 questions with the desired difficulty
        manager.shuffleQuestions();
        Question question;
        int points = 0;
        boolean timesUp = false;

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println("--- Question " + (i+1) + ": ---");
            question = manager.getQuestion();
            // Display the chosen question
            System.out.println(question);

            // Get the user's answer
            char answerChar = input.next().toUpperCase().charAt(0);
            while (answerChar != 'A' && answerChar != 'B' && answerChar != 'C' && answerChar != 'D') {
                System.out.println("Invalid entry. Please try again.");
                answerChar = input.next().toUpperCase().charAt(0);
            }

            // Determine if the answer was correct and assign a point if it was
            if (question.isCorrectAnswer(answerChar)) {
                System.out.println("Correct!");
                points++;
            } else {
                System.out.println("Wrong answer.");
                System.out.println(question.correctAnswer());
            }
        }

        // After the ten questions have been asked, display the results
        System.out.println();
        if (points == 10) {
            System.out.println("You got 10/10 questions right! You must be a genius.");
        } else if (points > 7) {
            System.out.println("Nice! You got " + points + "/10 right!");
        } else if (points > 3) {
            System.out.println(points + "/10 is decent. Could be better though.");
        } else if (points > 0) {
            System.out.println("Only " + points + "/10 ? That's a terrible score!");
        } else {
            System.out.println("Literally not a single answer was correct. Go read a book.");
        }
        System.out.println();
        System.out.println();
    }

    public void modify(String fileLocation) throws IOException {
        System.out.println("What modification would you like to make?");
        System.out.println("A. Add a question");
        System.out.println("B. Quit");

        char inputChar;
        while ((inputChar = input.next().toUpperCase().charAt(0)) != 'A' && inputChar != 'B') {
            System.out.println("Invalid Entry");
        }
        switch (inputChar) {
            case 'A':
                input.nextLine();
                // Get the question
                System.out.println("Enter the question and press enter");
                String question = input.nextLine();

                List<Answer> answers = new ArrayList<>();
                // Get the correct answer
                System.out.println("Enter the correct answer and press enter");
                answers.add(new Answer(input.nextLine(), true));

                // Get three incorrect answers
                for (int i = 0; i < 3; i++) {
                    System.out.println("Enter an incorrect answer:");
                    answers.add(new Answer(input.nextLine(), false));
                }

                // Get the difficulty rating
                System.out.println("Enter the difficulty rating (1 - 10 inclusive):");
                int rating = input.nextInt();
                while (rating < 1 || rating > 10) {
                    System.out.println("Invalid rating.");
                    rating = input.nextInt();
                }

                // Construct and submit the question
                System.out.println("Storing question...");
                manager.storeQuestion(fileLocation, new Question(question, answers, rating));
                System.out.println("Your question has been added to the game.");
                break;
            default:
                return;
        }
    }

    public void options() {
        System.out.println("Options menu");
        System.out.println("A. Toggle Default difficulty");
        System.out.println("B. Toggle Timed play");
        System.out.println("C. Exit");
        char inputChar;
        while ((inputChar = input.next().toUpperCase().charAt(0)) != 'A' && inputChar != 'B' && inputChar != 'C') {
            System.out.println("Invalid Entry");
        }
        switch (inputChar) {
            case 'A':
                askDifficulty = !askDifficulty;
                System.out.println("The game will " + (askDifficulty ? "" : "not ") + "request a difficulty level.");
                break;
            case 'B':
                timedPlay = !timedPlay;
                System.out.println("Timer is " + (timedPlay ? "en" : "dis") + "abled.");
            default:
                break;
        }
        // TODO Timed play option?
    }
}
