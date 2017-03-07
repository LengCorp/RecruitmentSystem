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
import javax.persistence.TypedQuery;
import javax.rmi.CORBA.Util;
import javax.transaction.Transactional;

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

    public String addUser(String name, String surname, String ssn, String email, String password, String username){

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

    public int getPersonIDbyUserName(String username){
        username = username.toLowerCase();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.username LIKE :custUsername", Person.class)
                .setParameter("custUsername", username);

        return query.getResultList().get(0).getPersonId();
    }
}
