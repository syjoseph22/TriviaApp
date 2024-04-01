package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        this.question = qAndAs.getFirst();
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

    public class Answer {
        enum category {
            WrongAnswer,
            RightAnswer
        }

        String answer;
        boolean correct;

        public Answer(String answer, boolean correct) {
            this.answer = answer;
            this.correct = correct;
        }


        public void setAsCorrect() {
            correct = true;
        }
        public void setAsIncorrect() {
            correct = false;
        }

        public boolean isCorrect() {
            return correct;
        }

    }



}
