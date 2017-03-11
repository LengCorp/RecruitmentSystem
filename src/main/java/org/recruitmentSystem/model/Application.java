package org.recruitmentSystem.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hiden on 3/8/2017.
 */
public class Application implements Serializable{
    private int applicationID;
    Person person;
    private List<Availability> availability;
    List<CompetenceListItem> competences;
    private String fullName;
    public Application(Person person){
        competences = new ArrayList<CompetenceListItem>();
        this.person = person;
        fullName = this.person.getName() +" "+ person.getSurname();
    }

    public void  addCompetence(CompetenceProfile profile, Competence name){
        applicationID = profile.getCompetenceId();
        competences.add(new CompetenceListItem(profile,name));
    }
    public List<Availability> getAvailability(){
        return availability;
    }
    public void addAvailabilities(List<Availability> availability){
        this.availability = availability;
    }
    public List<CompetenceListItem> getCompetences(){
        return competences;
    }

    public int getPersonId() {
        return person.getPersonId();
    }

    public String getEmail() {
        return person.getEmail();
    }

    public String getUsername() {
        return person.getUsername()!=null && person.getUsername().length()>1? person.getUsername() : "________" ;
    }

    public String getSsn() {
        return person.getSsn();
    }

    public String getName() {
        return person.getName();
    }

    public String getSurname() {
        return person.getSurname();
    }
    public void setFullName(String name){
        fullName = name;
    }
    public String getFullName(){
        return person.getName() + " " + person.getSurname();
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public class CompetenceListItem implements Serializable{
        private int competenceId;
        private int competenceProfileId;
        private BigDecimal yearsOfExperience;
        private String competenceName;

        public CompetenceListItem(CompetenceProfile profile, Competence competenceName){
            this.competenceId = profile.getCompetenceId();
            this.competenceProfileId = profile.getCompetenceProfileId();
            this.competenceName  = competenceName.getName();
            this.yearsOfExperience  = profile.getYearsOfExperience();
        }

        public BigDecimal getYearsOfExperience() {
            return yearsOfExperience;
        }

        public int getCompetenceProfileId() {
            return competenceProfileId;
        }

        public int getCompetenceId() {
            return competenceId;
        }

        public String getCompetenceName() {
            return competenceName;
        }
    }

}
