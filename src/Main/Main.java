package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner keyboard = new Scanner(System.in);
    static QuestionManager questions = new QuestionManager();
    public static void main(String[] args) throws IOException {
        boolean quit=false;
        System.out.println("Welcome to the game.");
        do{
            System.out.println("Please select one of the following options.");
            System.out.println("1) Start a game.\n2) Modify the rules.\n3) Modify the game's questions.\n4) Quit.");
            int userChoice=dataValidation(4);
            quit=userChoice<0?false:menuSelect(userChoice);
        }while (!quit);
        System.out.println("Hello world!");
    }
    public static int dataValidation(int availableOptions){
        int userChoice=-1;
        try {
            userChoice=keyboard.nextInt();
            keyboard.nextLine();
        }
        catch (Exception e){
            return -1;
        }
        if((userChoice<=0)||(userChoice>availableOptions))
            userChoice=-1;
        return userChoice;
    }

    private static boolean menuSelect(int userChoice) throws FileNotFoundException {
        switch (userChoice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                modifyQuestions();
                break;
            case 4:
                return true;
//                break;
        }
        return false;
    }
    public static void modifyQuestions() throws FileNotFoundException {
        System.out.println("Please select one of the following:");
        System.out.println("1) Add a question.\n2) Load a list of questions in from a files.\n3) Save the questions to a file.\n4) Remove a question.");
        int userChoice=dataValidation(4);
        switch (userChoice){
            case 1:
                addQuestion();
                break;
            case 2:
                System.out.println("Please enter the file location.");
                File file = new File(keyboard.nextLine());
                questions.loadQuestionsFromFile(file);
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
    private static void addQuestion(){
        String[] qAndas = new String[5];
        System.out.println("Please enter the question.");
        qAndas[0]=keyboard.nextLine();
        System.out.println("Please enter the \"Correct\" answer.");
        qAndas[1]=keyboard.nextLine();
        for(int i=0;i<3;i++){
            System.out.println("Please enter filler answer number "+(i+1)+".");
            qAndas[i+2]=keyboard.nextLine();
        }
        int rating=0;
        do{
            if(rating==-1)
                System.out.println("Input is invalid.");
            System.out.println("Please enter difficulty rating between 1 and 10. Enter 11 for non-rated questions");
            rating=dataValidation(11);
        }while (rating==-1);
        questions.addQuestion(qAndas,1,rating);
    }
}