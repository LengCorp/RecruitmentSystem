package org.RecruitmentSystem;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 * Created by daseel on 2/5/17.
 */
@Named("testFront")
@SessionScoped
public class TestFront {

    private String hue;

    @EJB
    TestEJB ejb;

    public TestFront(){
        hue = "hue";
    }

    public String getHue() {

        return hue;
    }

    public void setHue(String s) {
        // hue
    }

    public String getBajs(){
        return ejb.bajs();
    }

    public String getShit() {
        return ejb.shit();
    }
}
