/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Question;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author winnh
 */
public class QuestionDTO implements Serializable{
    private String qID;
    private String subId;
    private String question;
    private String conrrectAnswer;
    private String a;
    private String b;
    private String c;
    private String d;
    private Date createDate;
    private boolean status;

    public QuestionDTO() {
    }

    public QuestionDTO(String qID, String subId, String question, String conrrectAnswer, String A, String B, String C, String D, Date createDate, boolean status) {
        this.qID = qID;
        this.subId = subId;
        this.question = question;
        this.conrrectAnswer = conrrectAnswer;
        this.a = A;
        this.b = B;
        this.c = C;
        this.d = D;
        this.createDate = createDate;
        this.status = status;
    }



    public String getqID() {
        return qID;
    }

    public void setqID(String qID) {
        this.qID = qID;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getConrrectAnswer() {
        return conrrectAnswer;
    }

    public void setConrrectAnswer(String conrrectAnswer) {
        this.conrrectAnswer = conrrectAnswer;
    }

    public String getA() {
        return a;
    }

    public void setA(String A) {
        this.a = A;
    }

    public String getB() {
        return b;
    }

    public void setB(String B) {
        this.b = B;
    }

    public String getC() {
        return c;
    }

    public void setC(String C) {
        this.c = C;
    }

    public String getD() {
        return d;
    }

    public void setD(String D) {
        this.d = D;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
