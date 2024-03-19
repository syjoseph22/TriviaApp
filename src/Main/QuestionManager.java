package Main;

import java.util.ArrayDeque;
import java.util.Deque;

public class QuestionManager {
    Deque<Question>  questions;

    public QuestionManager() {
        questions = new ArrayDeque<Question>();
    }
}
