package org.recruitmentSystem.view;

import org.recruitmentSystem.Utils;
import org.recruitmentSystem.integration.PersonDAO;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.rmi.CORBA.Util;

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
    public String getUsername(){
        return username;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void submitRegistration() {
        success = null;
        success = personDAO.addUser(forename, surname, ssn, email, password, username);
        System.out.println(forename + " " + surname + " " + username + " " + ssn + " " + email + " " + password + " " + username);
    }
    public boolean getSubmitRegistration(){
        return success != null;
    }
    public String getSuccess(){
        System.out.println(success);
        return  success;}

    public Boolean getCreateUser(){return success!=null;}

}
