/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Temporary
 */
@Entity
@Table(name = "SubjectType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectType.findAll", query = "SELECT s FROM SubjectType s"),
    @NamedQuery(name = "SubjectType.findById", query = "SELECT s FROM SubjectType s WHERE s.id = :id"),
    @NamedQuery(name = "SubjectType.findBySubjectTypeName", query = "SELECT s FROM SubjectType s WHERE s.subjectTypeName = :subjectTypeName"),
    @NamedQuery(name = "SubjectType.findByDescription", query = "SELECT s FROM SubjectType s WHERE s.description = :description"),
    @NamedQuery(name = "SubjectType.findByActive", query = "SELECT s FROM SubjectType s WHERE s.active = :active")})
public class SubjectType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "SubjectTypeName")
    private String subjectTypeName;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Active")
    private boolean active;
    @OneToMany(mappedBy = "subjectTypeId")
    private List<Subject> subjectList;

    public SubjectType() {
    }

    public SubjectType(Integer id) {
        this.id = id;
    }

    public SubjectType(Integer id, boolean active) {
        this.id = id;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectTypeName() {
        return subjectTypeName;
    }

    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
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
        if (!(object instanceof SubjectType)) {
            return false;
        }
        SubjectType other = (SubjectType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.SubjectType[ id=" + id + " ]";
    }
    
}
