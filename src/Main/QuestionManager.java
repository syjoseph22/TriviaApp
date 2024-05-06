package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class QuestionManager {
    ArrayList<Question> questions;
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
        List<String> answers = new ArrayList<>() {
        };
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
}
