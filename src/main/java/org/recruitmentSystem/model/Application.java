package org.recruitmentSystem.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Hiden on 3/8/2017.
 */
public class Application {
    Person person;
    List<CompetenceListItem> competences;

    public Application(Person person){
        this.person = person;
    }
    public void  addCompetence(CompetenceProfile profile, Competence name){
        competences.add(new CompetenceListItem(profile,name));
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
        return person.getUsername();
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

    class CompetenceListItem{
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
