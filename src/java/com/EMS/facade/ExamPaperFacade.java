/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EMS.facade;

import com.EMS.entities.ExamPaper;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Saurav
 */
@Stateless
public class ExamPaperFacade extends AbstractFacade<ExamPaper> {
    @PersistenceContext(unitName = "EMSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamPaperFacade() {
        super(ExamPaper.class);
    }
    
}
