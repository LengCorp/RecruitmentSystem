package org.RecruitmentSystem.model;

/**
 * Created by Hiden on 2/8/2017.
 */
public class User {
    String firstName;
    String lastName;
    String personNR;
    String phoneNumber;
    String mail;

    String country;
    String city;
    String address;
    public User(String firstName, String lastName,String personNR,String phoneNumber, String mail, String country, String city, String address) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.personNR = personNR;
        this.phoneNumber = phoneNumber;
        this.mail = mail;

        this.country=country;
        this.city = city;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonNR() {
        return personNR;
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

    public void setNewAddress(String country, String city, String address){
        this.country=country;
        this.city = city;
        this.address = address;
    }
}
