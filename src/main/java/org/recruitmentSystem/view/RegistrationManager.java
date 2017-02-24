package org.recruitmentSystem.view;

import org.recruitmentSystem.Utils;
import org.recruitmentSystem.integration.PersonDAO;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class RegistrationManager {
    @EJB
    private PersonDAO personDAO;

    private String forename;
    private String surname;
    private String ssn;
    private String email;
    private String password;
    private String username;
    private String success;
    private String fail;
    private String password2;

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getSsn() {
        return ssn;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public String getUsername(){
        return username;
    }

    public void setForename(String forename) {
        if (Utils.regexNames(forename)==forename)
            this.forename = forename;
    }

    public void setSurname(String surname) {
        if (Utils.regexNames(surname) ==surname)
        this.surname = surname;
    }

    public void setSsn(String ssn) {
        if (Utils.regexSSN(ssn).equals(ssn))
            this.ssn = ssn;
    }

    public void setEmail(String email) {
        if (Utils.regexEmail(email).equals(email))
            this.email = email;
    }

    public void setPassword(String password) {
        if (Utils.regexPasswords(password)==password)
            this.password = password;
    }

    public void setPassword2(String password2) {
        if (Utils.regexPasswords(password2)==password2)
            this.password2 = password2;
    }

    public void setUsername(String username) {
        if (Utils.regexUsernames(username)==username)
            this.username = username;
    }

    public void submitRegistration() {
        success = null;
        if (password.length()>7 && password.equals(password2)) {
            if (forename != null && surname != null && ssn != null && email != null && password != null && username != null) {
                success = personDAO.addUser(forename, surname, ssn, email, password, username);
            } else {
                fail = "Fill following empty boxes with correct data.";
            }
        }else {
                fail = "Passwords does not match. Or is shorter than 7 characters";
        }
    }
    public boolean getSubmitRegistration(){
        return success != null || fail !=null;
    }
    public String getSuccess(){
        System.out.println(success);
        return  success;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public String getFail() {
        return fail;
    }

    public Boolean getCreateUser(){return success!=null;}

}
