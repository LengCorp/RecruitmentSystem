package org.recruitmentSystem.integration;

import org.recruitmentSystem.model.Application;
import org.recruitmentSystem.model.Competence;
import org.recruitmentSystem.model.CompetenceProfile;
import org.recruitmentSystem.model.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Hiden on 3/8/2017.
 */
@Transactional
@Stateless
public class ApplicationDAO {

    @PersistenceContext(unitName = "RSPU",  type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Application getApplication(String ssn) throws PersistenceException{
        PersonDAO dao = new PersonDAO();
        Person person =  dao.getPerson(ssn);
        Application application = new Application(person);
        List<CompetenceProfile> list = em.createNamedQuery("competenceProfile.getCompetenceProfileWithPersonId", CompetenceProfile.class).setParameter("personId", person.getPersonId()).getResultList();
        for (CompetenceProfile item: list) {
            Competence competence = em.createNamedQuery("competence.getCompetenceById", Competence.class).setParameter("competenceId", item.getCompetenceId()).getSingleResult();
            application.addCompetence(item, competence);
        }
    return application;
    }

}
