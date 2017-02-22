package org.recruitmentSystem.integration;

import org.recruitmentSystem.model.Competence;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

/**
 * Created by Simon on 2017-02-22.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateful
public class CompetenceDAO {

    @PersistenceContext(unitName = "RSPU")
    private EntityManager em;

    public CompetenceDAO(){
    }

    public ArrayList fetchCompetences(int competence_id, String name){

        Competence competence = new Competence();

        competence.setCompetenceId(competence_id);
        competence.setName(name);

        return null;
    }
}