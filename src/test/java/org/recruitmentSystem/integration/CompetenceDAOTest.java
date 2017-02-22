package org.recruitmentSystem.integration;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.recruitmentSystem.model.Competence;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import static org.junit.Assert.*;

/**
 * Created by Simon on 2017-02-22.
 */
@RunWith(Arquillian.class)
public class CompetenceDAOTest {

    @Inject
    private CompetenceDAO competenceDAO;

    @PersistenceContext(unitName = "RSPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Inject
    private UserTransaction userTransaction;

    @Transactional
    @Before
    public void setUp() throws Exception {

        userTransaction.begin();
        em.joinTransaction();

        em.persist(new Competence("boss"));
        em.persist(new Competence("snorunge"));
        em.persist(new Competence("jobbare"));
        em.persist(new Competence("person1"));
        em.persist(new Competence("person2"));
        em.persist(new Competence("person3"));
        em.persist(new Competence("person4"));
        em.persist(new Competence("person5"));
        em.persist(new Competence("vakt"));
        em.persist(new Competence("biljettgalning"));
        em.persist(new Competence("ballongförsäljare"));

        userTransaction.commit();
    }

    @Transactional
    @Test
    public void findAllLengthShouldBeTen() throws Exception {
        assertEquals(10, competenceDAO.findAll().size());
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

}
