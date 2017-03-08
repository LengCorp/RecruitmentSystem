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
import org.recruitmentSystem.model.Application;
import org.recruitmentSystem.model.Person;

import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@RunWith(Arquillian.class)
public class ApplicationDAOTest {
    @PersistenceContext(unitName = "RSPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @Inject
    private UserTransaction userTransaction;

    @Inject
    private ApplicationDAO appDAO;

    @Test
    public void getApplication() throws Exception {
        Application bla = appDAO.getApplicationBySSN("19671212-1211");
        System.out.println(bla.getSsn()+"  "+bla.getName());
        for (Application.CompetenceListItem item: bla.getCompetences() ) {
            System.out.println(item.getCompetenceId()+"\t"+item.getCompetenceName()+"\t"+item.getYearsOfExperience());
        }
    }
    @Before
    public void deploy() throws Exception {
        userTransaction.begin();
        em.joinTransaction();

        Query q = em.createNativeQuery("BEGIN " + "CREATE TABLE role (\n" +
                "\n" +
                "role_id BIGINT PRIMARY KEY,\n" +
                "\n" +
                "name VARCHAR(255)\n" +
                "\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE person (\n" +
                "\n" +
                "person_id BIGINT PRIMARY KEY,\n" +
                "\n" +
                "name VARCHAR(255),\n" +
                "\n" +
                "surname VARCHAR(255),\n" +
                "\n" +
                "ssn VARCHAR(255),\n" +
                "\n" +
                "email VARCHAR(255),\n" +
                "\n" +
                "password VARCHAR(255),\n" +
                "\n" +
                "role_id BIGINT REFERENCES role,\n" +
                "\n" +
                "username VARCHAR(255)\n" +
                "\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE availability (\n" +
                "\n" +
                "availability_id BIGINT PRIMARY KEY,\n" +
                "\n" +
                "person_id BIGINT REFERENCES person,\n" +
                "\n" +
                "from_date DATE,\n" +
                "\n" +
                "to_date DATE\n" +
                "\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE competence (\n" +
                "\n" +
                "competence_id BIGINT PRIMARY KEY,\n" +
                "\n" +
                "name VARCHAR(255)\n" +
                "\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE competence_profile (\n" +
                "\n" +
                "competence_profile_id BIGINT PRIMARY KEY,\n" +
                "\n" +
                "person_id BIGINT REFERENCES person,\n" +
                "\n" +
                "competence_id BIGINT REFERENCES competence,\n" +
                "\n" +
                "years_of_experience NUMERIC(4,2)\n" +
                "\n" +
                ");" + " END;");
        Query q1 = em.createNativeQuery("BEGIN " + "INSERT INTO role (role_id, name) VALUES (1, 'recruit');\n" +
                "INSERT INTO role (role_id, name) VALUES (2, 'applicant');\n" +
                "INSERT INTO person (person_id, name, surname, username, password, role_id)\n" +
                "VALUES (1, 'Greta', 'Borg', 'borg', 'wl9nk23a', 1);\n" +
                "INSERT INTO person (person_id, name, surname, ssn, email, role_id)\n" +
                "VALUES (2, 'Per', 'Strand', '19671212-1211', 'per@strand.kth.se', 2);\n" +
                "INSERT INTO availability (availability_id, person_id, from_date, to_date)\n" +
                "VALUES (1, 2, '2014-02-23', '2014-05-25');\n" +
                "INSERT INTO availability (availability_id, person_id, from_date, to_date)\n" +
                "VALUES (2, 2, '2014-07-10', '2014-08-10');\n" +
                "INSERT INTO competence (competence_id, name)\n" +
                "VALUES (1, 'Korvgrillning');\n" +
                "INSERT INTO competence (competence_id, name)\n" +
                "VALUES (2, 'Karuselldrift');\n" +
                "INSERT INTO competence_profile (competence_profile_id, person_id,\n" +
                "competence_id, years_of_experience)\n" +
                "VALUES (1, 2, 1, 3.5);\n" +
                "INSERT INTO competence_profile (competence_profile_id, person_id,\n" +
                "competence_id, years_of_experience)\n" +
                "VALUES (2, 2, 2, 2.0);" + " END;");
        q.executeUpdate();
        q1.executeUpdate();
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
        return ShrinkWrap.create(JavaArchive.class).addPackage(PersonDAO.class.getPackage()).addPackage(Person.class.getPackage())
                .addClass(PersonDAO.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addClass(PersonDAO.class).addAsResource("META-INF/test-persistence.xml",
                        "META-INF/persistence.xml");
    }

}
