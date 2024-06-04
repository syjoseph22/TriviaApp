package Main;

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
