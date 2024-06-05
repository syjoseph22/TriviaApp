package Main;

import java.util.*;

public class Question {


    /**
     * The question to be displayed to the user.
     */
    private String question;

    /**
     * The different answers they will be given.
     */
    private List<Answer> answers;

    /**
     * Difficulty from 1-10
     */
    private int difficulty = 0;

    /**
     * Constructor using already made list of Answer objects.
     * @param question The question.
     * @param answers The list of Answer objects.
     */
    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    /**
     * Constructor using a question string, already made list of Answer objects,
     * and a difficulty.
     * @param question The question.
     * @param answers The list of Answer objects.
     * @param difficulty The difficulty level of the question
     */
    public Question(String question, List<Answer> answers, int difficulty) {
        this(question, answers);
        this.difficulty = difficulty;
    }


    /**
     * Constructor using an array of Answer objects.
     * @param question The question
     * @param answers The list of Answer objects.
     */
    public Question(String question, Answer[] answers) {
        this(question, List.of(answers));
    }

    /**
     * Constructor using a list of Strings including both the question and the answers.
     * @param qAndAs The List of strings with the info.
     * @param correctAnswer The index of which String should be put in as the correct answer.
     */
    public Question(List<String> qAndAs, int correctAnswer) {
        this.question = qAndAs.get(0);
        answers = new ArrayList<>();
        for (int i = 1; i < qAndAs.size(); i++) {
            answers.add(new Answer(qAndAs.get(i), i == correctAnswer));
        }
    }


    /**
     * Constructor using an array of Strings including both the question and the answers.
     * @param qAndAs The array of strings with the info.
     * @param correctAnswer The index of which String should be put in as the correct answer.
     */
    public Question(String[] qAndAs, int correctAnswer) {
        this(List.of(qAndAs), correctAnswer);
    }

    public void setDifficulty(int difficulty) {
        if (difficulty > 0 && difficulty <= 10)
            this.difficulty = difficulty;

        throw new IllegalArgumentException("Only from 1 to 10 is allowed");
    }

    public boolean isRated() {
        return difficulty != 0;
    }


    public List<String> dataDump() {
        List<String> temp = new ArrayList<>();
        Integer correct=0;
        temp.add(question);
        for(int i =0;i<answers.size();i++){
            if(answers.get(i).isCorrect())
                correct=i+1;
            temp.add(answers.get(i).getAnswer());
        }
        temp.add(correct.toString());
        return temp;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    // This method takes a char and outputs whether this is
    // the correct answer.
    public boolean isCorrectAnswer(char answerChar) {
        int answerIndex = switch (answerChar) {
            case 'A' -> 0;
            case 'B' -> 1;
            case 'C' -> 2;
            case 'D' -> 3;
            default -> throw new IllegalArgumentException();
        };
        return answers.get(answerIndex).isCorrect();
    }

    public Answer correctAnswer() {
       return answers.stream().filter(a -> a.isCorrect()).findFirst().get();
    }

    public List<Answer> incorrectAnswers() {
        return answers.stream().filter(a -> !a.isCorrect()).toList();
    }

    @Override
    public String toString() {
        Collections.shuffle(answers);
        return "Difficulty level: " + getDifficulty() + "\n" + question +
                "\nA: " + answers.get(0).getAnswer() +
                "\nB: " + answers.get(1).getAnswer() +
                "\nC: " + answers.get(2).getAnswer() +
                "\nD: " + answers.get(3).getAnswer();
    }
}
