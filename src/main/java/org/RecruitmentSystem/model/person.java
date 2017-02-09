package org.RecruitmentSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Hiden on 2/8/2017.
 */
@Entity(name="person")
public class person implements Serializable{

    private static final long serialVersionUID = 598634L;

    @Id
    @Column(name= "person_id", nullable = false)
    private int person_id;
    @Column(name= "firstname", nullable = false)
    private String firstName;
    @Column(name= "surname", nullable = false)
    private String surName;
    @Column(name= "firstname", nullable = false)
    private String ssn;
    @Column(name= "email", nullable = false)
    private String mail;

    @Column(name= "username", nullable = false)
    private String username;
    @Column(name= "password", nullable = false)
    private String password;
    @Column(name= "role_id", nullable = false)
    private int role_id;
    public person(){
        this.firstName = null;
        this.surName = null;
        this.person_id = 0;
        this.ssn = null;
        this.mail = null;

        this.username = null;
        this.password = null;
        this.role_id = 0;
    }
    public person(String firstName, String surName, int person_id, String ssn, String mail, String username, String password, int role_id) {

        this.firstName = firstName;
        this.surName = surName;
        this.person_id = person_id;
        this.ssn = ssn;
        this.mail = mail;

        this.username=username;
        this.password = password;
        this.role_id = role_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surName;
    }

    public int getPerson_id() {
        return person_id;
    }

    public String getSSN() {
        return ssn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        mail = mail;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }
}
