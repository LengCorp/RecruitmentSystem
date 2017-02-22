package org.recruitmentSystem.integration;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.recruitmentSystem.model.Competence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.UserTransaction;

import static org.junit.Assert.assertEquals;

/**
 * Created by Simon on 2017-02-22.
 */
@RunWith(Arquillian.class)
public class CompetenceDAOTest {

    @Inject
    private CompetenceDAO competenceDAO;

    @Inject
    private UserTransaction userTransaction;

    @PersistenceContext(unitName = "RSPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Before
    public void setUp() throws Exception {

        userTransaction.begin();
        em.joinTransaction();

        em.persist(new Competence("person1"));
        em.persist(new Competence("person2"));
        em.persist(new Competence("person3"));
        em.persist(new Competence("person4"));
        em.persist(new Competence("boss"));
        em.persist(new Competence("snorunge"));
        em.persist(new Competence("jobbare"));
        em.persist(new Competence("vakt"));
        em.persist(new Competence("biljettgalning"));
        em.persist(new Competence("ballongförsäljare"));

        userTransaction.commit();
        em.clear();

    }

    @After
    public void tearDown() throws Exception {

        userTransaction.begin();
        em.joinTransaction();

        em.createQuery("DELETE FROM Competence").executeUpdate();
        userTransaction.commit();

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CompetenceDAO.class)
                .addClass(Competence.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/test-persistence.xml",
                        "META-INF/persistence.xml");
    }

    @Test
    public void findAllLengthShouldBeTen() throws Exception {
        assertEquals(10, competenceDAO.findAll().size());

    }

    @Test
    public void findAllIDOfPersonFourShouldBeFour() throws Exception {
        assertEquals(4, ((Competence) competenceDAO.findAll().get(3)).getCompetenceId());
    }

}
