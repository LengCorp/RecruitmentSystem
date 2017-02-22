package org.recruitmentSystem.facades;

import org.recruitmentSystem.integration.PersonDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by Hiden on 2/8/2017.
 */
@Stateless
public class Facade {

        PersonDAO personHandler;
    public Facade(){
        super();
        personHandler  = new PersonDAO();
    }
    public String createUser(String name, String surname, String ssn, String email, String password, String username){
        //CHECK
        name = name.replaceAll("[A-Za-z]","");
        surname = surname.replaceAll("[A-Za-z]","");
        ssn = ssn.replaceAll("[A-Za-z0-9]","");
        email = email.replaceAll("[A-Za-z]","");
        password = password.replaceAll("[^A-Za-z0-9]","");
        username = username.replaceAll("[A-Za-z0-9]","");
        //CHECKED AND CALLS USER();
        return personHandler.addUser(name, surname, ssn, email, password, username);
    }
}
