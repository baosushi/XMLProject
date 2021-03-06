/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Temporary
 */
@Entity
@Table(name = "Block")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "block")
@XmlType(name = "", propOrder = {
    "id",
    "blockName",
    "description",
    "active"})
@NamedQueries({
    @NamedQuery(name = "Block.findAll", query = "SELECT b FROM Block b"),
    @NamedQuery(name = "Block.findById", query = "SELECT b FROM Block b WHERE b.id = :id"),
    @NamedQuery(name = "Block.findByBlockName", query = "SELECT b FROM Block b WHERE b.blockName = :blockName"),
    @NamedQuery(name = "Block.findByDescription", query = "SELECT b FROM Block b WHERE b.description = :description"),
    @NamedQuery(name = "Block.findByActive", query = "SELECT b FROM Block b WHERE b.active = :active")})
public class Block implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_JUST_FOR_TEST", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Basic(optional = false)
    @Column(name = "ID")
    @XmlElement(required = true)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "BlockName")
    @XmlElement(required = true)
    private String blockName;
    @Column(name = "Description")
    @XmlElement(required = false)
    private String description;
    @Basic(optional = false)
    @Column(name = "Active")
    @XmlElement(required = true)
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blockId")
    @XmlTransient
    private List<BlockOfMajor> blockOfMajorList;
    @OneToMany(mappedBy = "blockId")
    @XmlTransient
    private List<SubjectOfBlock> subjectOfBlockList;

    public Block() {
    }

    public Block(Integer id) {
        this.id = id;
    }

    public Block(Integer id, String blockName, boolean active) {
        this.id = id;
        this.blockName = blockName;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
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
    public List<BlockOfMajor> getBlockOfMajorList() {
        return blockOfMajorList;
    }

    public void setBlockOfMajorList(List<BlockOfMajor> blockOfMajorList) {
        this.blockOfMajorList = blockOfMajorList;
    }

    @XmlTransient
    public List<SubjectOfBlock> getSubjectOfBlockList() {
        return subjectOfBlockList;
    }

    public void setSubjectOfBlockList(List<SubjectOfBlock> subjectOfBlockList) {
        this.subjectOfBlockList = subjectOfBlockList;
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
        if (!(object instanceof Block)) {
            return false;
        }
        Block other = (Block) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Block[ id=" + id + " ]";
    }

}
