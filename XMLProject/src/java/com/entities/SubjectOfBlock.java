/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Temporary
 */
@Entity
@Table(name = "SubjectOfBlock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectOfBlock.findAll", query = "SELECT s FROM SubjectOfBlock s"),
    @NamedQuery(name = "SubjectOfBlock.findById", query = "SELECT s FROM SubjectOfBlock s WHERE s.id = :id"),
    @NamedQuery(name = "SubjectOfBlock.findBySubjectCoefficient", query = "SELECT s FROM SubjectOfBlock s WHERE s.subjectCoefficient = :subjectCoefficient"),
    @NamedQuery(name = "SubjectOfBlock.findByActive", query = "SELECT s FROM SubjectOfBlock s WHERE s.active = :active")})
public class SubjectOfBlock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SubjectCoefficient")
    private Integer subjectCoefficient;
    @Column(name = "Active")
    private Boolean active;
    @JoinColumn(name = "BlockId", referencedColumnName = "ID")
    @ManyToOne
    private Block blockId;
    @JoinColumn(name = "SubjectID", referencedColumnName = "ID")
    @ManyToOne
    private Subject subjectID;

    public SubjectOfBlock() {
    }

    public SubjectOfBlock(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectCoefficient() {
        return subjectCoefficient;
    }

    public void setSubjectCoefficient(Integer subjectCoefficient) {
        this.subjectCoefficient = subjectCoefficient;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Block getBlockId() {
        return blockId;
    }

    public void setBlockId(Block blockId) {
        this.blockId = blockId;
    }

    public Subject getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Subject subjectID) {
        this.subjectID = subjectID;
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
        if (!(object instanceof SubjectOfBlock)) {
            return false;
        }
        SubjectOfBlock other = (SubjectOfBlock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.SubjectOfBlock[ id=" + id + " ]";
    }
    
}
