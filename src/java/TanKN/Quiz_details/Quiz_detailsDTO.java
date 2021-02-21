/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Quiz_details;

import TanKN.Question.QuestionDTO;
import java.io.Serializable;

/**
 *
 * @author winnh
 */
public class Quiz_detailsDTO implements Serializable{
    private String QuizID;
    private String questionID;
    private QuestionDTO question;
    private String answer;
    private String correctAnswer;

    public Quiz_detailsDTO() {
    }

    public Quiz_detailsDTO(String QuizID, String questionID, String answer, String correctAnswer) {
        this.QuizID = QuizID;
        this.questionID = questionID;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }
    
    
    public String getQuizID() {
        return QuizID;
    }

    public void setQuizID(String QuizID) {
        this.QuizID = QuizID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
}
