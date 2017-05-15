package org.recruitmentSystem.integration;

import org.recruitmentSystem.model.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Hiden on 3/8/2017.
 */
@Transactional
@Stateless
public class ApplicationDAO {

    @PersistenceContext(unitName = "RSPU",  type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    private  DateFormat format;
    public ApplicationDAO(){
        format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
    }

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
    public Application getApplicationById(int pid) throws PersistenceException{
        PersonDAO dao = new PersonDAO(em);
        Person person =  dao.getPersonById(pid);
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
    public List<Application> getApplicationsWithPeriod(String from, String to) throws PersistenceException{
        List<Application> applications = new ArrayList<Application>();
        List<Availability> availabilities = em.createNamedQuery("availability.getAllThatAreWithinFromTo", Availability.class).setParameter("fromDate",from).setParameter("toDate", to).getResultList();

        for (Availability avail: availabilities ) {
            applications.add(getApplicationById(avail.getPersonId()));
        }
        return applications;
    }
    public List<Application> getApplicationsWithRegistrationDate(   ){return  null;}

    public List<Application> getApplicationsWithCompetence(String competence){
        List<Application> applications = new ArrayList<>();
        List<CompetenceProfile> profiles = em.createNamedQuery("competenceProfile.getCompetenceProfilesWithCompetenceNamed", CompetenceProfile.class).setParameter("name", competence).getResultList();
        for (CompetenceProfile profile : profiles){
            applications.add(getApplicationById(profile.getPersonId()));
        }
        return  applications;
    }

    public List<Application> getApplicationsWithApplicantName(String forename, String surname){
        PersonDAO personDAO = new PersonDAO(em);
        List<Person> people = personDAO.getPeopleWithName(forename, surname);
        List<Application> applications = new ArrayList<>();
        for (Person person : people) {
            applications.add(getApplicationById((person.getPersonId())));
        }
        return null;
    }
    public List<Application> getApplicationSearchResult(String forename, String surname, String competence, String from, String to, String registrationDate) throws PersistenceException {
        java.sql.Date registrationDateDate = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(registrationDate));
            registrationDateDate = new java.sql.Date(cal.getTime().getTime());

        } catch (Exception e) {
        }
        List<Person> people = em.createNamedQuery("person.getPersonWhereAllParametersMatch", Person.class)
                .setParameter("competence", competence)
                .setParameter("forename", forename)
                .setParameter("surname", surname)
                .setParameter("fromDate", from)
                .setParameter("toDate", to)
                .setParameter("regDate",  registrationDateDate)
                .getResultList();
        List<Application> applications = new ArrayList<>();
        for (Person person : people){
            applications.add(getApplicationById(person.getPersonId()));
        }
        return applications;
    }

}
