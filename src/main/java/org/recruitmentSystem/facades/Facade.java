package org.recruitmentSystem.facades;

import org.recruitmentSystem.integration.PersonDAO;

/**
 * Created by Hiden on 2/8/2017.
 */
public class Facade {
        PersonDAO personHandler;
    public Facade(){
        super();
        personHandler  = new PersonDAO();
    }
    public String createUser(int personId, String name, String surname, String ssn, String email, String password, int roleId, String username){
        //CHECK
        name = name.replaceAll("[A-Za-z]","");
        surname = surname.replaceAll("[A-Za-z]","");
        ssn = ssn.replaceAll("[A-Za-z0-9]","");
        email = email.replaceAll("[A-Za-z]","");
        password = password.replaceAll("[^A-Za-z0-9]","");
        username = username.replaceAll("[A-Za-z0-9]","");
        //CHECKED AND CALLS USER();
        return personHandler.addUser(personId, name, surname, ssn, email, password, roleId, username);
    }
}
