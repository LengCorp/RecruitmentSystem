package org.RecruitmentSystem.Integration;

import org.RecruitmentSystem.model.Person;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.transaction.Transactional;

/**
 * Created by Hiden on 2/9/2017.
 */
@Transactional
public class PersonDAO {

    EntityManager em;
    public PersonDAO(){
        super();
        em = Persistence.createEntityManagerFactory("RSPU").createEntityManager();
    }

    public String addUser( int personId, String name, String surname, String ssn, String email, String password, int roleId, String username){

        Person person = new Person();

        person.setPersonId(personId);
        person.setName(name);
        person.setSurname(surname);
        person.setSsn(ssn);
        person.setEmail(email);
        person.setPassword(password);
        person.setUsername(username);
        person.setRoleId(roleId);
        try{
        em.persist(person);
        }catch (Exception ex){
            ex.printStackTrace();
            return "fail";
        }
        em.close();
        System.out.println("success");
        return "success";
    }

}
