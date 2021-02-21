/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Question;

import java.io.Serializable;

/**
 *
 * @author winnh
 */
public class CreateNewQuestionErrors implements Serializable{
    private String questionIDError;
    private String questionError;
    private String aError;
    private String bError;
    private String answerError;
    private String subIdError;

    public CreateNewQuestionErrors(String questionIDError, String questionError, String aError, String bError, String answerError, String subIdError) {
        this.questionIDError = questionIDError;
        this.questionError = questionError;
        this.aError = aError;
        this.bError = bError;
        this.answerError = answerError;
        this.subIdError = subIdError;
    }

    public CreateNewQuestionErrors() {
    }

    public String getQuestionIDError() {
        return questionIDError;
    }

    public void setQuestionIDError(String questionIDError) {
        this.questionIDError = questionIDError;
    }

    public String getQuestionError() {
        return questionError;
    }

    public void setQuestionError(String questionError) {
        this.questionError = questionError;
    }

    public String getaError() {
        return aError;
    }

    public void setaError(String aError) {
        this.aError = aError;
    }

    public String getbError() {
        return bError;
    }

    public void setbError(String bError) {
        this.bError = bError;
    }

    public String getAnswerError() {
        return answerError;
    }

    public void setAnswerError(String answerError) {
        this.answerError = answerError;
    }

    public String getSubIdError() {
        return subIdError;
    }

    public void setSubIdError(String subIdError) {
        this.subIdError = subIdError;
    }
    
    
}
