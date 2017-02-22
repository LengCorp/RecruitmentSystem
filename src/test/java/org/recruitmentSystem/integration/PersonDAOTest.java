package org.recruitmentSystem.integration;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.recruitmentSystem.model.Person;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by daseel on 2/22/17.
 */
@RunWith(Arquillian.class)
@Transactional

public class PersonDAOTest {

    @Inject
    private PersonDAO personDAO;

    @PersistenceContext(unitName = "RSPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;


    @Test
    public void addUser() throws Exception {


        String retu = personDAO.addUser("emil", "lengman",
                "1995-05-28-6998", "asd@asd.se", "asd", 0, "emillen");
        Person p = em.find(Person.class, 1);
        assertEquals("emil", p.getName());
        assertEquals("success", retu);
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
