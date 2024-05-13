package Main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner keyboard = new Scanner(System.in);
    static QuestionManager questions = new QuestionManager();
    public static void main(String[] args) {
        boolean quit=false;
        System.out.println("Welcome to the game.");
        do{
            System.out.println("Please select one of the following options.");
            System.out.println("1) Start a game.\n2) Modify the rules.\n3) Modify the game's questions.\n4) Quit.");
            int userChoice=dataValidation(4);
            quit=userChoice<1?false:menuSelect(userChoice);
        }while (!quit);
        System.out.println("Thanks for playing.");
    }
    public static int dataValidation(int availableOptions){
        int userChoice = -1;
        try {
            userChoice=keyboard.nextInt();
            keyboard.nextLine();
        }
        catch (Exception e){
            return -1;
        }
        if((userChoice<=0)||(userChoice>availableOptions))
            userChoice = -1;
        return userChoice;
    }

    private static boolean menuSelect(int userChoice) {
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
    public static void modifyQuestions() {
        System.out.println("Please select one of the following:");
        System.out.println("1) Add a question.\n2) Load a list of questions in from a files." +
                "\n3) Save the questions to a file.\n4) List questions\n5) Remove a question.");
        int userChoice=dataValidation(5);
        switch (userChoice){
            case 1:
                addQuestion();
                break;
            case 2:
                if(!questions.loadQuestionsFromFile(getAddressForFileFromUser()))
                    System.out.println("There was a error writing to the file.");
                break;
            case 3:
                writeQuestionsToFile();
                break;
            case 4:
                List<String> allQandAs = questions.getQuestionsAndAnswers();
                for(String s: allQandAs)
                    System.out.println(s);
                break;
            case 5:
                System.out.println("Please enter the question number you would like to remove.");
                questions.removeQuestion(dataValidation(questions.questions.size()));
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
            rating=rating==11?0:rating;
        }while (rating==-1);
        questions.addQuestion(qAndas,1,rating);
    }
    private static File getAddressForFileFromUser(){
        boolean realFile=true;
        File fileLocation = null;
        do {
            System.out.println("Please enter the file address.");
            try {
                fileLocation = new File(keyboard.nextLine());
                fileLocation.canRead();
            }
            catch (Exception e) {
                System.out.println("There is an issue with the file address. Please try again.");
                realFile=false;
            }
        }while (!realFile);
        return fileLocation;
    }
    private static void writeQuestionsToFile() {
        boolean addressValid=true;
        File fileLocation=null;
         do {
            System.out.println("Please enter a address for the file location.");
            try {
                fileLocation=new File(keyboard.nextLine());
                fileLocation.canWrite();
            }
            catch (Exception e){
                System.out.println("Can't write to that location.");
                addressValid=false;
            }
            if (addressValid)
                addressValid = questions.storeQuestions(fileLocation);
        }while (!addressValid);
    }
}