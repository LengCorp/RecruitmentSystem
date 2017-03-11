package org.recruitmentSystem.view;

import org.recruitmentSystem.Utils;
import org.recruitmentSystem.integration.ApplicationDAO;
import org.recruitmentSystem.integration.PersonDAO;
import org.recruitmentSystem.model.Application;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.persistence.PersistenceException;
import javax.persistence.PostLoad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ManagedBean
public class ApplicationManager implements Serializable {

    @EJB
    ApplicationDAO appDAO;
    @EJB
    PersonDAO personDAO;

    private List<Application> applications = new ArrayList<Application>();
    private UIComponent submitButton;
    private Date dataFrom, dataTo;
    private Date RegistrationDate;
    private String Competence;
    private String forename, surname;
    private String searchWith;
    private String success;

    String[] searchItems = new String[]{
            "Period available",
            "Registration date",
            "Applicant name",
            "Competence"};
    private List<String> itemList = Arrays.asList(searchItems);


    public void SearchWithPeriod(){

    }
    public void SearchWithRegDate(){

    }
    public void searchName(){
        success = null;
        System.out.println("nlaaaaa ");
       try {

            applications.add(appDAO.getApplicationBySSN("19671212-1211"));
            System.out.println("nlaaaaa");
            success = "true";
        }catch (Exception ex){
            success = "false";
            System.err.println(ex.getMessage());
        }
    }
    public boolean getSearchName(){
        return success!=null;
    }
    public void SearchWithCompetence(){

    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getCompetence() {
        return Competence;
    }

    public void setCompetence(String competence) {
        Competence = competence;
    }

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        RegistrationDate = registrationDate;
    }

    public Date getDataTo() {
        return dataTo;
    }

    public void setDataTo(Date dataTo) {
        this.dataTo = dataTo;
    }

    public Date getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(Date dataFrom) {
        this.dataFrom = dataFrom;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public String getSearchWith() {
        return searchWith;
    }

    public void setSearchWith(String searchWith) {
        this.searchWith = searchWith;
    }

    public List<Application> getApplications() {
        System.out.println("oing");
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public UIComponent getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(UIComponent submitButton) {
        this.submitButton = submitButton;
    }

    //public boolean getSearchForApplication() {
     //   return success != null;
   // }

   }
