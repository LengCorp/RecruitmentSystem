package org.recruitmentSystem.integration;

import org.recruitmentSystem.model.Competence;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public List findAll(){

        Query query = em.createNamedQuery("Competence.findAll", Competence.class);

        return query.getResultList();
    }
}