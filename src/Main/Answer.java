package Main;

public class Answer {
    enum category {
        WrongAnswer,
        RightAnswer
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    private String answer;
    private boolean correct;

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

    @Override
    public String toString() {
        return answer;
    }
}
