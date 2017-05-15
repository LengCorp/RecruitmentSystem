package org.recruitmentSystem.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by daseel on 2/9/17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="availability.getAvailabilityById",
                query="SELECT c FROM Availability c where c.personId = :personId"),
        @NamedQuery(name="availability.availabilities",
                query="SELECT c FROM Competence c"),
        @NamedQuery(name="availability.getAllThatAreWithinFromTo", query = "SELECT c FROM Availability c WHERE c.fromDate < :fromDate  AND c.toDate > :toDate")
})
@Table(name = "availability")
public class Availability {
    private int availabilityId;
    private int personId;
    private Date fromDate;
    private Date toDate;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "availability_id")
    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
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
    @Column(name = "from_date")
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "to_date")
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Availability that = (Availability) o;

        if (availabilityId != that.availabilityId) return false;
        if (personId != that.personId) return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = availabilityId;
        result = 31 * result + personId;
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        return result;
    }
}
