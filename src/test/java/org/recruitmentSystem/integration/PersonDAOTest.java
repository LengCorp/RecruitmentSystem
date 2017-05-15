package org.recruitmentSystem.integration;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.recruitmentSystem.model.Person;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import static org.junit.Assert.assertEquals;

/**
 * Created by daseel on 2/22/17.
 */
@RunWith(Arquillian.class)
@Transactional

public class PersonDAOTest {

    @Inject
    private PersonDAO personDAO;

    @Inject
    private UserTransaction userTransaction;

    @PersistenceContext(unitName = "RSPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;


    @Before
    public void setUp() throws Exception {

        userTransaction.begin();
        em.joinTransaction();

        em.persist(new Person("person1"));
        em.persist(new Person("person2"));
        em.persist(new Person("person3"));
        em.persist(new Person("person4"));
        em.persist(new Person("person5"));
        em.persist(new Person("borg"));

        userTransaction.commit();
        em.clear();

    }

    @Test
    public void addUser() throws Exception {


        String retu = personDAO.addUser("emil", "lengman",
                "1995-05-28-6998", "asd@asd.se", "asd", "emillen");
        //   Person p = em.find(Person.class, 1);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(PersonDAO.class.getPackage()).addPackage(Person.class.getPackage())
                .addClass(PersonDAO.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addClass(PersonDAO.class).addAsResource("META-INF/test-persistence.xml",
                        "META-INF/persistence.xml");
    }

}
