package org.recruitmentSystem.integration;

import org.recruitmentSystem.model.Availability;
import org.recruitmentSystem.model.CompetenceProfile;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;

/**
 * Created by Simon on 2017-03-12.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateful
public class AvailabilityDAO {

    @PersistenceContext(unitName = "RSPU")
    private EntityManager em;

    public AvailabilityDAO(){
    }

    public boolean addAvailability(int personId, Date fromDate, Date toDate){

        Availability availability = new Availability();

        availability.setPersonId(personId);
        availability.setFromDate(fromDate);
        availability.setToDate(toDate);

        try{
            em.persist(availability);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}