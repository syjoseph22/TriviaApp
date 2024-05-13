package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class QuestionManager {
    ArrayList<Question> questions;
    private static int pointer=0;
    public QuestionManager() {
        this.questions = new ArrayList<>();
    }
    private List<String> getQandAs(Scanner o){
        List<String> answers = new ArrayList<>();
        for(int i =0; i<5;i++)
            answers.add(o.nextLine());
        return answers;
    }
    public boolean loadQuestionsFromFile(File fileLocation) {
        Scanner database;
        try {
            database = new Scanner(fileLocation);
        }
        catch (FileNotFoundException e){
            return false;
        }
        while (database.hasNext()) {
            questions.add(new Question(getQandAs(database), database.nextInt()));
            database.nextLine();
        }
        return true;
    }
    public boolean storeQuestions(File fileLocation) {
        FileWriter storageFile;
        try{
            storageFile=new FileWriter(fileLocation);
            List<String> qAndaList;
            for(int i =0;i<questions.size();i++){
                qAndaList = questions.get(i).dataDump();
                Iterator<String> qAndaInter = qAndaList.iterator();
                while (qAndaInter.hasNext()){
                    storageFile.write(qAndaInter.next()+"\n");
                }
            }
            storageFile.close();
        }
        catch (Exception e){
            System.out.println("There was an error writing to the file.");
            return false;
        }
        return true;
    }
    public void addQuestion(String[] qAndas,int correctPointer,int rating){
        questions.add(new Question(qAndas,correctPointer));
        questions.get(questions.size()-1).setDifficulty(rating);
    }
    public Question getQuestion(){
        if(pointer>questions.size()-1)
            throw new IndexOutOfBoundsException();
        return questions.get(pointer++);
    }
    public Question getRatedQuestion(int difficultyRating){
        if(pointer>questions.size()-1)
            throw new IndexOutOfBoundsException();
        while(!(pointer>questions.size()-1)){
            if (questions.get(pointer++).getDifficulty()==difficultyRating)
                return questions.get(pointer-1);
        }
            throw new NoSuchElementException();
    }
    public List<String> getQuestionsAndAnswers(){
        List<String> allQuestionsAndAnswers = new ArrayList<>();
        for (int i=0;i<questions.size();i++) {
            allQuestionsAndAnswers.add("Question Number "+(i+1)+"\nQuestion: "+questions.get(i).getQuestion());
            allQuestionsAndAnswers.add("Right Answer: "+questions.get(i).getRightAnswer());
            for (String answers:questions.get(i).getFillerAnswers())
                allQuestionsAndAnswers.add("Filler: "+answers);
        }
        return allQuestionsAndAnswers;
    }
    public void removeQuestion(int questionNumber){
        questions.remove(questionNumber-1);
    }
//    public List<String> dataDump() {
//        List<String> temp = new ArrayList<>();
//        Integer correct=0;
//        temp.add(question);
//        for(int i =0;i<answers.size();i++){
//            if(answers.get(i).isCorrect())
//                correct=i+1;
//            temp.add(answers.get(i).answer);
//        }
//        temp.add(correct.toString());
//        return temp;
//    }
}
