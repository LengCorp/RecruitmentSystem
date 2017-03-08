package org.recruitmentSystem.model;

import javax.persistence.*;

/**
 * Created by daseel on 2/9/17.
 */

@Entity
@NamedQueries({
        @NamedQuery(name="person.getPersonBySSN",
                query="SELECT c FROM Person c where c.ssn = :ssn"),
        @NamedQuery(name="person.getAllPeople",
                query="SELECT c FROM Person c"),
        @NamedQuery(name="person.getPersonByUsername",
                query="SELECT c FROM Person c where c.username = :username"),
})
@Table(name = "person")
public class Person {
    private int personId;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    private String password;
    private int roleId;
    private String username;


    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "person_id")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "ssn")
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (personId != person.personId) return false;
        if (roleId != person.roleId) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (surname != null ? !surname.equals(person.surname) : person.surname != null) return false;
        if (ssn != null ? !ssn.equals(person.ssn) : person.ssn != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (password != null ? !password.equals(person.password) : person.password != null) return false;
        if (username != null ? !username.equals(person.username) : person.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (ssn != null ? ssn.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + roleId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
