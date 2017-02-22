package org.recruitmentSystem.model;

import javax.persistence.*;

/**
 * Created by daseel on 2/9/17.
 */
@Entity
@Table(name = "competence")
public class Competence {
    private int competenceId;
    private String name;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "competence_id")
    public int getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(int competenceId) {
        this.competenceId = competenceId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Competence that = (Competence) o;

        if (competenceId != that.competenceId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = competenceId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
