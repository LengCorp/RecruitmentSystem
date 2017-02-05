package org.RecruitmentSystem;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by daseel on 2/5/17.
 */
@Stateless
@LocalBean
public class TestEJB {

    @PersistenceContext(unitName = "RecSysPU")
    private EntityManager em;

    public String bajs() {

        return "bajs";
    }

}
