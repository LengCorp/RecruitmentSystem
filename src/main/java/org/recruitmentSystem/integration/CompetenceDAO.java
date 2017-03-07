package org.recruitmentSystem.integration;

import org.recruitmentSystem.model.Competence;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

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
    public List<Competence> findAll(){

        TypedQuery<Competence> query = em.createQuery("SELECT c FROM Competence c", Competence.class);

        return query.getResultList();
    }

}