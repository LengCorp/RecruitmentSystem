package org.recruitmentSystem.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by daseel on 2/9/17.
 */
@Entity
@Table(name = "competence_profile")
public class CompetenceProfile {
    private int competenceProfileId;
    private int personId;
    private int competenceId;
    private BigDecimal yearsOfExperience;

    @Id
    @Column(name = "competence_profile_id")
    public int getCompetenceProfileId() {
        return competenceProfileId;
    }

    public void setCompetenceProfileId(int competenceProfileId) {
        this.competenceProfileId = competenceProfileId;
    }

    @Basic
    @Column(name = "person_id")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "competence_id")
    public int getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(int competenceId) {
        this.competenceId = competenceId;
    }

    @Basic
    @Column(name = "years_of_experience")
    public BigDecimal getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(BigDecimal yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompetenceProfile that = (CompetenceProfile) o;

        if (competenceProfileId != that.competenceProfileId) return false;
        if (personId != that.personId) return false;
        if (competenceId != that.competenceId) return false;
        if (yearsOfExperience != null ? !yearsOfExperience.equals(that.yearsOfExperience) : that.yearsOfExperience != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (competenceProfileId ^ (competenceProfileId >>> 32));
        result = 31 * result + personId;
        result = 31 * result + competenceId;
        result = 31 * result + (yearsOfExperience != null ? yearsOfExperience.hashCode() : 0);
        return result;
    }
}
