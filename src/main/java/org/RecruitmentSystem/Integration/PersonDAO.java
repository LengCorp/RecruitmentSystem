package org.RecruitmentSystem.Integration;

import org.RecruitmentSystem.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Hiden on 2/9/2017.
 */

public class PersonDAO {
    private EntityManagerFactory emf;
    public PersonDAO(){
        super();
        this.emf = Persistence.createEntityManagerFactory("RSPU");
    }

    public String addUser( int personId, String name, String surname, String ssn, String email, String password, int roleId, String username){
        EntityManager em = beginTransaction();

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
            return "fail";
        }
        commitTransaction(em);

        return "success";
    }

    private EntityManager beginTransaction() {
        EntityManager entity = emf.createEntityManager();
        entity.getTransaction().begin();
        return entity;
    }

    private void commitTransaction(EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }
}
