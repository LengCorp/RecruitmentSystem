package org.recruitmentSystem.view;

import org.recruitmentSystem.Utils;
import org.recruitmentSystem.integration.PersonDAO;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
public class RegistrationManager {
    @EJB
    private PersonDAO personDAO;

    private UIComponent submitButton;

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
        fail = null;
        StringBuilder message = new StringBuilder();
        boolean status=true;
        if (forename == null){
            message.append("Please enter a valid forname.\n");
            status = false;
        }
        if (surname == null){
            message.append("Please enter a valid surname.\n");
            status = false;
        }
        if (ssn == null){
            message.append("Please enter a valid SSN.\n");
            status = false;
        }
        if(email == null){
            message.append("Please enter a valid email.\n");
            status = false;
        }
        if (password.length()<7 || password == null){
            message.append("Please enter a valid password longer then 7 characters.\n");
            status = false;
        }
        else if(password2 == null || !password.equals(password2)){
            message.append("The passwords does not match.\n");
            status = false;
        }
        if (status==false){
            FacesMessage msg = new FacesMessage(message.toString());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(submitButton.getClientId(context), msg);
            fail = message.toString();
            return;
        }
        success = null;
        success = personDAO.addUser(forename, surname, ssn, email, password, username);
        try {
            FacesContext.getCurrentInstance().getExternalContext().dispatch("/registrationSuccess.xhtml");
        } catch (IOException e) {
            return;
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

    public UIComponent getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(UIComponent submitButton) {
        this.submitButton = submitButton;
    }
}
