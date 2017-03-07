package org.recruitmentSystem.view;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;


/**
 * Created by daseel on 3/7/17.
 */
@Named(value = "user")
@RequestScoped
public class UserManager {


    @Inject
    private Principal myUser;

    public boolean isLoggedIn() {

        return !myUser.getName().equals("anonymous");
    }

    public String getUserName() {

        return isLoggedIn() ? myUser.getName() : "";
    }
}
