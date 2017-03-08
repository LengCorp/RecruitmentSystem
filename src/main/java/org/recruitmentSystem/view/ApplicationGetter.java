package org.recruitmentSystem.view;

import org.recruitmentSystem.Utils;
import org.recruitmentSystem.integration.ApplicationDAO;
import org.recruitmentSystem.model.Application;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by Hiden on 3/8/2017.
 */
@ManagedBean
public class ApplicationGetter {
    @EJB
    ApplicationDAO appDAO;
    Application application;

    private int personId;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    private String username;
    private String success;
    private String fail;

    public void SearchForApplication(String ssn) {
        success = null;
        if (ssn != Utils.regexSSN(ssn)) {
            fail = "Not valid SSN";
            return;
        }
        try {
            application = appDAO.getApplication(ssn);
            personId = application.getPersonId();
            name = application.getName();
            surname = application.getSurname();
            email = application.getEmail();
            ;

            success = "success";
        } catch (PersistenceException ex) {
            fail = "Failed to get Application";
        }
    }

    public boolean getSearchForApplication() {
        return success != null;
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFail() {
        return fail;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }
}
