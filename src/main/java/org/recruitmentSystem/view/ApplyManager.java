package org.recruitmentSystem.view;

import org.recruitmentSystem.integration.CompetenceDAO;
import org.recruitmentSystem.integration.PersonDAO;
import org.recruitmentSystem.model.Availability;
import org.recruitmentSystem.model.Competence;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
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
    private int yearsOfExperience;
    private List<Competence> savedCompetences;
    private int chosenSavedCompetence;
    private Map<String, Integer> submittedCompetences;

    //availability part
    private Date fromDate;
    private Date toDate;
    private String from;
    private String to;
    private Map<Integer, Availability> availabilities;
    private int localID;
    private int chosenSavedAvailability;
    @Inject
    private UserManager userManager;
    @EJB
    private PersonDAO personDAO;


    @PostConstruct
    public void init() {
        competences = competenceDAO.findAll();
        availabilities = new HashMap<>();
        localID = 1;
        savedCompetences = new ArrayList<>();
        submittedCompetences = new HashMap<>();
    }

    public ApplyManager() {
    }

    public void saveDates() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.to = formatter.format(toDate);
        this.from = formatter.format(fromDate);
        Availability a = new Availability();
        a.setFromDate(new java.sql.Date(fromDate.getTime()));
        a.setToDate(new java.sql.Date(toDate.getTime()));
        int id = personDAO.getPersonIDbyUserName(userManager.getUserName());
        a.setPersonId(id);
        availabilities.put(localID++, a);
    }

    public void saveCompetenceInput() {

        if (competences != null) {
            Competence competenceToBeRemoved = new Competence();
            for (Competence competence : competences) {
                if (competence.getCompetenceId() == chosenCompetence) {
                    submittedCompetences.put(competence.getName(), yearsOfExperience);
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
            submittedCompetences.remove(competenceToBeRemoved.getName());
        }
    }

    public void deleteMarkedAvailability(){

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

    public String checkIfThereIsAnAvailability(){

        if (availabilities.size() > 0){
            return "success";
        } else {
            return "failure";
        }
    }

    public Map<String, Integer> getSubmittedCompetences() {
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

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
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
