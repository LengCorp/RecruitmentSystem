package org.recruitmentSystem.view;

import org.recruitmentSystem.integration.CompetenceDAO;
import org.recruitmentSystem.model.Competence;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Simon on 2017-03-05.
 */
@ManagedBean
@SessionScoped
public class ApplyManager {

    @EJB
    private CompetenceDAO competenceDAO;

    private List<Competence> competences;
    private int chosenCompetence;
    private int chosenSavedCompetence;
    private int yearsOfExperience;
    private Map<String, Integer> submittedCompetences;
    private List<Competence> savedCompetences;

    private UIComponent registerButton;
    private UIComponent deleteButton;

    @PostConstruct
    public void init() {
        competences = competenceDAO.findAll();
        savedCompetences = new ArrayList<>();
        submittedCompetences = new HashMap<>();
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

    //TODO test
    public String getSubmittedCompetences() {
        return submittedCompetences.toString();
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

    public UIComponent getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(UIComponent registerButton) {
        this.registerButton = registerButton;
    }

    public UIComponent getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(UIComponent deleteButton) {
        this.deleteButton = deleteButton;
    }
}
