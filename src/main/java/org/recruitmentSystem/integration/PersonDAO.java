package org.recruitmentSystem.integration;

import org.recruitmentSystem.Utils;
import org.recruitmentSystem.model.Competence;
import org.recruitmentSystem.model.Person;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.rmi.CORBA.Util;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Hiden on 2/9/2017.
 */
@Transactional
@Stateless
public class PersonDAO {

    @PersistenceContext(unitName = "RSPU",  type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public PersonDAO(){
    }

    public PersonDAO(EntityManager em){
        this.em = em;
    }

    public String addUser(String name, String surname, String ssn, String email, String password, String username) throws PersistenceException{

        Person person = new Person();


        System.out.println(name + " " + surname + " " + username + " " + ssn + " " + email + " " + password + " " + username);
        person.setName(Utils.regexNames(name));
        person.setSurname(Utils.regexNames(surname));
        person.setSsn(Utils.regexSSN(ssn));
        person.setEmail(Utils.regexEmail(email));
        person.setPassword(Utils.regexPasswords(password));
        person.setUsername(Utils.regexUsernames(username));
        person.setRoleId(1);
        try{
            em.persist(person);
        }catch (Exception ex){
            ex.printStackTrace();
            return "fail";
        }
        return "Registration success";
    }

    public Person getPersonBySSN(String ssn) throws PersistenceException {
        Person person= em.createNamedQuery("person.getPersonBySSN",Person.class).setParameter("ssn", ssn).getSingleResult();
        return person;
    } public Person getPersonByUsername(String username) throws PersistenceException {
        Person person= em.createNamedQuery("person.getPersonByUsername",Person.class).setParameter("username", username).getSingleResult();
        return person;
    }

    public List<Person> getAllPeople() throws PersistenceException{
        List<Person> people= em.createNamedQuery("person.getAllPeople",Person.class).getResultList();
        return people;

    }
}
