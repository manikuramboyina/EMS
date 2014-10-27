/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EMS.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mani
 */
@Entity
public class QuestionMultiChoice extends Question implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private Collection<String> choices;
    public Collection<String> getChoices() {
        return choices;
    }

    public void setChoices(Collection<String> choices) {
        this.choices = choices;
    }
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionMultiChoice)) {
            return false;
        }
        QuestionMultiChoice other = (QuestionMultiChoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.EMS.entities.QuestionMultiChoice[ id=" + id + " ]";
    }
    
}
