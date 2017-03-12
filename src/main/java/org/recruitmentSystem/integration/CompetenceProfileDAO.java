package org.recruitmentSystem.integration;

import org.recruitmentSystem.model.CompetenceProfile;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

/**
 * Created by Simon on 2017-03-12.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateful
public class CompetenceProfileDAO {

    @PersistenceContext(unitName = "RSPU")
    private EntityManager em;

    public CompetenceProfileDAO(){
    }

    public boolean addCompetenceProfile(int personId, int competenceId, BigDecimal yearsOfExperience){

        CompetenceProfile competenceProfile = new CompetenceProfile();

        competenceProfile.setCompetenceId(competenceId);
        competenceProfile.setPersonId(personId);
        competenceProfile.setYearsOfExperience(yearsOfExperience);

        try{
            em.persist(competenceProfile);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

}