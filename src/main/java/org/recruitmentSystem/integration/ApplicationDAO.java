package org.recruitmentSystem.integration;

import org.recruitmentSystem.model.*;

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

    public ApplicationDAO(){}

    public Application getApplicationBySSN(String ssn) throws PersistenceException{
        PersonDAO dao = new PersonDAO(em);
        Person person =  dao.getPersonBySSN(ssn);
        List<Availability> availability = em.createNamedQuery("availability.getAvailabilityById", Availability.class).setParameter("personId", person.getPersonId()).getResultList();
        Application application = new Application(person);
        application.addAvailabilities(availability);
        List<CompetenceProfile> list = em.createNamedQuery("competenceProfile.getCompetenceProfileWithPersonId", CompetenceProfile.class).setParameter("personId", person.getPersonId()).getResultList();
        for (CompetenceProfile item: list) {
            Competence competence = em.createNamedQuery("competence.getCompetenceById", Competence.class).setParameter("competenceId", item.getCompetenceId()).getSingleResult();
            application.addCompetence(item, competence);
        }
    return application;
    }

}
