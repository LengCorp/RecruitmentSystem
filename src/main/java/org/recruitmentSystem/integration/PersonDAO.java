package org.recruitmentSystem.integration;

import org.recruitmentSystem.model.Person;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

/**
 * Created by Hiden on 2/9/2017.
 */
public class PersonDAO {

    @PersistenceContext(unitName = "RSPU",  type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public PersonDAO(){
    }

    public String addUser(String name, String surname, String ssn, String email, String password, String username){

        Person person = new Person();

        person.setName(name);
        person.setSurname(surname);
        person.setSsn(ssn);
        person.setEmail(email);
        person.setPassword(password);
        person.setUsername(username);
        person.setRoleId(1);
        try{
            em.persist(person);
        }catch (Exception ex){
            ex.printStackTrace();
            return "fail";
        }
        System.out.println("success");
        return "success";
    }
}
