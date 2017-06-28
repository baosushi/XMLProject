/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Block")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Block.findAll", query = "SELECT b FROM Block b"),
    @NamedQuery(name = "Block.findById", query = "SELECT b FROM Block b WHERE b.id = :id"),
    @NamedQuery(name = "Block.findByBlockName", query = "SELECT b FROM Block b WHERE b.blockName = :blockName"),
    @NamedQuery(name = "Block.findByDescription", query = "SELECT b FROM Block b WHERE b.description = :description"),
    @NamedQuery(name = "Block.findByActive", query = "SELECT b FROM Block b WHERE b.active = :active")})
public class Block implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "BlockName")
    private String blockName;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blockId")
    private List<BlockOfMajor> blockOfMajorList;
    @OneToMany(mappedBy = "blockId")
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
        return "com.entity.Block[ id=" + id + " ]";
    }
    
}
