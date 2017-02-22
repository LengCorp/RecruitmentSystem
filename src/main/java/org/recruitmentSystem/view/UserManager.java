package org.recruitmentSystem.view;

import org.recruitmentSystem.facades.Facade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
@Named("Users")
@RequestScoped
public class UserManager {

    private int personId;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    private String password;
    private int roleId;
    private String username;
    private String success;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void createUser(){
        Facade userfacade = new Facade();
        success = null;
        success = userfacade.createUser(name, surname, ssn, email, password, username);
    }
    public String getSuccess(){ return  success;}
    public Boolean getCreateUser(){return success!=null;}
}
