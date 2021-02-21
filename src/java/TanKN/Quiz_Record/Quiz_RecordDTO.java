/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Quiz_Record;

import java.io.Serializable;

/**
 *
 * @author winnh
 */
public class Quiz_RecordDTO implements Serializable{
    private String QuizID;
    private String email;
    private String SubID;
    private float scores;

    public Quiz_RecordDTO(String QuizID, String email, String SubID, float scores) {
        this.QuizID = QuizID;
        this.email = email;
        this.SubID = SubID;
        this.scores = scores;
    }

    public Quiz_RecordDTO() {
    }

    public String getQuizID() {
        return QuizID;
    }

    public void setQuizID(String QuizID) {
        this.QuizID = QuizID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubID() {
        return SubID;
    }

    public void setSubID(String SubID) {
        this.SubID = SubID;
    }

    public float getScores() {
        return scores;
    }

    public void setScores(float scores) {
        this.scores = scores;
    }
    
}
