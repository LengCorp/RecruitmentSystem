package org.RecruitmentSystem.model;

/**
 * Created by Hiden on 2/8/2017.
 */
public class Person {
    String firstName;
    String surName;
    String person_id;
    String phoneNumber;
    String mail;

    String username;
    String password;
    String role_id;
    public Person(String firstName, String surName, String person_id, String phoneNumber, String mail, String username, String password, String role_id) {

        this.firstName = firstName;
        this.surName = surName;
        this.person_id = person_id;
        this.phoneNumber = phoneNumber;
        this.mail = mail;

        this.username=username;
        this.password = password;
        this.role_id = role_id;
    }

    public String getusername() {
        return username;
    }

    public String getpassword() {
        return password;
    }

    public String getrole_id() {
        return role_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getsurName() {
        return surName;
    }

    public String getperson_id() {
        return person_id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        mail = mail;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
