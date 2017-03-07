package org.recruitmentSystem.view;

import com.sun.security.auth.UserPrincipal;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;
import java.security.Principal;


/**
 * Created by daseel on 3/7/17.
 */
@Named(value = "user")
@SessionScoped
public class UserManager {


    @Inject
    private Principal myUser;

    @Inject
    private HttpSession session;

    public boolean isLoggedIn() {

        return !myUser.getName().equals("anonymous");
    }

    public String getUserName() {

        return isLoggedIn() ? myUser.getName() : "";
    }

    public String logout() {

        session.invalidate();
        return "logout";
    }
}
