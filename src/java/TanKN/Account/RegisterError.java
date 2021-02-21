/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Account;

import java.io.Serializable;

/**
 *
 * @author winnh
 */
public class RegisterError implements Serializable{
    private String emailError;
    private String passwordError;
    private String confirmNotMatch;
    private String emailExistError;

    public RegisterError(String emailError, String passwordError) {
        this.emailError = emailError;
        this.passwordError = passwordError;

    }

    public RegisterError() {
    }
    
    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }


    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getEmailExistError() {
        return emailExistError;
    }

    public void setEmailExistError(String emailExistError) {
        this.emailExistError = emailExistError;
    }
    
    
}
