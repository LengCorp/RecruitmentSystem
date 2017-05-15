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
import java.lang.reflect.Executable;
import java.text.ParseException;
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
    private String dataFrom, dataTo;
    private String registrationDate;
    private String competence;
    private String forename, surname;
    private String searchWith;
    private String success, success2;

    String[] searchItems = new String[]{
            "Period available",
            "Registration date",
            "Applicant name",
            "Competence"};
    private List<String> itemList = Arrays.asList(searchItems);

    @PostConstruct
    public void setDates(){

    }

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

    public void search(){
        System.out.println("ping");
        success2 = null;
        try {

            applications.addAll(appDAO.getApplicationSearchResult(forename, surname, competence, dataFrom, dataTo, registrationDate));
            success = "true";
        }catch (PersistenceException ex){
            System.out.println("malformed dates");
        }finally {
            surname = null;
            forename = null;
            dataTo = null;
            dataFrom = null;
            registrationDate=null;
        }
    }
    public boolean getSearch() {
        return success2 != null;
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
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDataTo() {
        return dataTo;
    }

    public void setDataTo(String dataTo) {
        this.dataTo = dataTo;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
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
