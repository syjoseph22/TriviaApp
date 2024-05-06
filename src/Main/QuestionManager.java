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
    public QuestionManager(File file) throws FileNotFoundException {
        this();
        if(!file.exists())
            throw new FileNotFoundException();
        Scanner database = new Scanner(file);
        while (database.hasNext()){
            questions.add(new Question(getQandAs(database),database.nextInt()));
            database.nextLine();
        }
    }

    private List<String> getQandAs(Scanner o){
        List<String> answers = new ArrayList<>();
        for(int i =0; i<5;i++)
            answers.add(o.nextLine());
        return answers;
    }
    public void storeQuestions(String fileLocation) throws IOException {
        FileWriter storageFile = new FileWriter(fileLocation);
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
}
