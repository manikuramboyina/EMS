/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EMS.entities;

import com.EMS.enums.QuestionTypes;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author mani
 */
@Entity
public class Question extends AbstractEntity implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    private String text;
    
    @ManyToOne
    private CourseModule coursemodule;
    
    @Basic(optional = false)
    private Integer marks;
    
    @Enumerated(EnumType.STRING) 
    private QuestionTypes questionTypes;
        
    @OneToMany
    private Collection<Subject> subjects;
    
    private String createdBy;
    
    private Date createdOn;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
    @Version
    private Long version;
    
    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CourseModule getCoursemodule() {
        return coursemodule;
    }

    public void setCoursemodule(CourseModule coursemodule) {
        this.coursemodule = coursemodule;
    }


    public Collection<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<Subject> subjects) {
        this.subjects = subjects;
    }

    public QuestionTypes getQuestionTypes() {
        return questionTypes;
    }

    public void setQuestionTypes(QuestionTypes questionTypes) {
        this.questionTypes = questionTypes;
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.EMS.entities.Question[ id=" + id + " ]";
    }
    
}
