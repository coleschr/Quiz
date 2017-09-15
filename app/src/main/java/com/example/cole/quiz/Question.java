package com.example.cole.quiz;

/**
 * Created by Cole on 9/15/17.
 */

public class Question {
    private String questionText;
    private boolean answer;

    public Question(String questionText, boolean answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean isAnswer() {
        return answer;
    }

    //check answer method that returns whether the supplied answer matches the real answer
    /**
     * @param userAnswer what the user selected
     * @return true if answers match, false if otherwise
     */
    public boolean checkAnswer(boolean userAnswer){
        return answer == userAnswer;
    }

}
