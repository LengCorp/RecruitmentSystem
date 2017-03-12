package org.recruitmentSystem.view;

import org.recruitmentSystem.integration.AvailabilityDAO;
import org.recruitmentSystem.integration.CompetenceDAO;
import org.recruitmentSystem.integration.CompetenceProfileDAO;
import org.recruitmentSystem.integration.PersonDAO;
import org.recruitmentSystem.model.Availability;
import org.recruitmentSystem.model.Competence;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Simon on 2017-03-05.
 */
@ManagedBean
@SessionScoped
public class ApplyManager {

    //competence part
    @EJB
    private CompetenceDAO competenceDAO;
    private List<Competence> competences;
    private int chosenCompetence;
    private BigDecimal yearsOfExperience;
    private List<Competence> savedCompetences;
    private int chosenSavedCompetence;
    private Map<Integer, BigDecimal> submittedCompetences;

    //availability part
    private Date fromDate;
    private Date toDate;
    private Map<Integer, Availability> availabilities;
    private int localId;
    private int chosenSavedAvailability;
    @Inject
    private UserManager userManager;
    @EJB
    private PersonDAO personDAO;
    private int personId;

    //submit part
    @EJB
    private CompetenceProfileDAO competenceProfileDAO;
    private String successCompetence;
    @EJB
    private AvailabilityDAO availabilityDAO;
    private String successAvailability;


    @PostConstruct
    public void init() {
        competences = competenceDAO.findAll();
        successCompetence = "unset";
        successAvailability = "unset";
        availabilities = new HashMap<>();
        localId = 1;
        savedCompetences = new ArrayList<>();
        submittedCompetences = new HashMap<>();
        personId = personDAO.getPersonIDbyUserName(userManager.getUserName());
    }

    public ApplyManager() {
    }

    public void submitApplication() {

        int personId = personDAO.getPersonIDbyUserName(userManager.getUserName());

        for (Map.Entry<Integer, BigDecimal> entry : submittedCompetences.entrySet()) {
            if (!competenceProfileDAO.addCompetenceProfile(personId, entry.getKey(), entry.getValue())) {
                successCompetence = "failure";
                return;
            }
        }

        for (Map.Entry<Integer, Availability> entry : availabilities.entrySet()) {
            if (!availabilityDAO.addAvailability(personId, entry.getValue().getFromDate(), entry.getValue().getToDate())) {
                successAvailability = "failure";
                return;
            }
        }

        successCompetence = "success";
        successAvailability = "success";

        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveDates() {
        Availability a = new Availability();
        a.setFromDate(new java.sql.Date(fromDate.getTime()));
        a.setToDate(new java.sql.Date(toDate.getTime()));
        a.setPersonId(personId);
        availabilities.put(localId++, a);
    }

    public void saveCompetenceInput() {

        if (competences != null) {
            Competence competenceToBeRemoved = new Competence();
            for (Competence competence : competences) {
                if (competence.getCompetenceId() == chosenCompetence) {
                    submittedCompetences.put(competence.getCompetenceId(), yearsOfExperience);
                    savedCompetences.add(competence);
                    competenceToBeRemoved = competence;
                }
            }

            competences.remove(competenceToBeRemoved);
        }

    }

    public void deleteMarkedCompetence() {

        if (savedCompetences != null) {
            Competence competenceToBeRemoved = new Competence();
            for (Competence competence : savedCompetences) {
                if (competence.getCompetenceId() == chosenSavedCompetence) {
                    competences.add(competence);
                    competenceToBeRemoved = competence;
                }
            }

            savedCompetences.remove(competenceToBeRemoved);
            submittedCompetences.remove(competenceToBeRemoved.getCompetenceId());
        }
    }

    public void deleteMarkedAvailability() {

        if (availabilities != null) {

            availabilities.remove(chosenSavedAvailability);
        }
    }

    public String checkIfThereIsACompetence() {

        if (savedCompetences.size() > 0) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String checkIfThereIsAnAvailability() {

        if (availabilities.size() > 0) {
            return "success";
        } else {
            return "failure";
        }
    }

    public void destroySession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getSuccess() {
        return (successCompetence.equals("success") && successAvailability.equals("success"));
    }

    public Map<Integer, BigDecimal> getSubmittedCompetences() {
        return submittedCompetences;
    }

    public Map<Integer, Availability> getAvailabilities() {
        return availabilities;
    }

    public int getChosenSavedAvailability() {
        return chosenSavedAvailability;
    }

    public void setChosenSavedAvailability(int chosenSavedAvailability) {
        this.chosenSavedAvailability = chosenSavedAvailability;
    }

    public int getChosenSavedCompetence() {
        return chosenSavedCompetence;
    }

    public void setChosenSavedCompetence(int chosenSavedCompetence) {
        this.chosenSavedCompetence = chosenSavedCompetence;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public List<Competence> getSavedCompetences() {
        return savedCompetences;
    }

    public void setSavedCompetences(List<Competence> savedCompetences) {
        this.savedCompetences = savedCompetences;
    }

    public int getChosenCompetence() {
        return chosenCompetence;
    }

    public void setChosenCompetence(int chosenCompetence) {
        this.chosenCompetence = chosenCompetence;
    }

    public BigDecimal getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(BigDecimal yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
