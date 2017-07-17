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
@Table(name = "BlockOfMajor")
@XmlRootElement(name = "blockOfMajor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "acceptedEntryLastYear",
    "baseScoreLastYear",
    "description",
    "active",
    "blockId"})
@NamedQueries({
    @NamedQuery(name = "BlockOfMajor.findAll", query = "SELECT b FROM BlockOfMajor b"),
    @NamedQuery(name = "BlockOfMajor.findById", query = "SELECT b FROM BlockOfMajor b WHERE b.id = :id"),
    @NamedQuery(name = "BlockOfMajor.findByMainSubjectCode", query = "SELECT b FROM BlockOfMajor b WHERE b.mainSubjectCode = :mainSubjectCode"),
    @NamedQuery(name = "BlockOfMajor.findByAcceptedEntryLastYear", query = "SELECT b FROM BlockOfMajor b WHERE b.acceptedEntryLastYear = :acceptedEntryLastYear"),
    @NamedQuery(name = "BlockOfMajor.findByBaseScoreLastYear", query = "SELECT b FROM BlockOfMajor b WHERE b.baseScoreLastYear = :baseScoreLastYear"),
    @NamedQuery(name = "BlockOfMajor.findByAcceptedEntryYearBeforeLast", query = "SELECT b FROM BlockOfMajor b WHERE b.acceptedEntryYearBeforeLast = :acceptedEntryYearBeforeLast"),
    @NamedQuery(name = "BlockOfMajor.findByBaseScoreYearBeforeLast", query = "SELECT b FROM BlockOfMajor b WHERE b.baseScoreYearBeforeLast = :baseScoreYearBeforeLast"),
    @NamedQuery(name = "BlockOfMajor.findByDescription", query = "SELECT b FROM BlockOfMajor b WHERE b.description = :description"),
    @NamedQuery(name = "BlockOfMajor.findByActive", query = "SELECT b FROM BlockOfMajor b WHERE b.active = :active")})
public class BlockOfMajor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @XmlElement(required = true)
    private Integer id;
    @Column(name = "MainSubjectCode")
    @XmlTransient
    private String mainSubjectCode;
    @Column(name = "AcceptedEntryLastYear")
    @XmlElement(required = true)
    private Integer acceptedEntryLastYear;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BaseScoreLastYear")
    @XmlElement(required = true)
    private Double baseScoreLastYear;
    @Column(name = "AcceptedEntryYearBeforeLast")
    @XmlTransient
    private Integer acceptedEntryYearBeforeLast;
    @Column(name = "BaseScoreYearBeforeLast")
    @XmlTransient
    private Double baseScoreYearBeforeLast;
    @Column(name = "Description")
    @XmlElement(required = false)
    private String description;
    @Basic(optional = false)
    @Column(name = "Active")
    @XmlElement(required = true)
    private boolean active;
    @JoinColumn(name = "BlockId", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @XmlElement(name = "block", required = true)
    private Block blockId;
    @JoinColumn(name = "MajorId", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @XmlTransient
    private Major majorId;

    public BlockOfMajor() {
    }

    public BlockOfMajor(Integer id) {
        this.id = id;
    }

    public BlockOfMajor(Integer id, boolean active) {
        this.id = id;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainSubjectCode() {
        return mainSubjectCode;
    }

    public void setMainSubjectCode(String mainSubjectCode) {
        this.mainSubjectCode = mainSubjectCode;
    }

    public Integer getAcceptedEntryLastYear() {
        return acceptedEntryLastYear;
    }

    public void setAcceptedEntryLastYear(Integer acceptedEntryLastYear) {
        this.acceptedEntryLastYear = acceptedEntryLastYear;
    }

    public Double getBaseScoreLastYear() {
        return baseScoreLastYear;
    }

    public void setBaseScoreLastYear(Double baseScoreLastYear) {
        this.baseScoreLastYear = baseScoreLastYear;
    }

    public Integer getAcceptedEntryYearBeforeLast() {
        return acceptedEntryYearBeforeLast;
    }

    public void setAcceptedEntryYearBeforeLast(Integer acceptedEntryYearBeforeLast) {
        this.acceptedEntryYearBeforeLast = acceptedEntryYearBeforeLast;
    }

    public Double getBaseScoreYearBeforeLast() {
        return baseScoreYearBeforeLast;
    }

    public void setBaseScoreYearBeforeLast(Double baseScoreYearBeforeLast) {
        this.baseScoreYearBeforeLast = baseScoreYearBeforeLast;
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

    public Block getBlockId() {
        return blockId;
    }

    public void setBlockId(Block blockId) {
        this.blockId = blockId;
    }

    public Major getMajorId() {
        return majorId;
    }

    public void setMajorId(Major majorId) {
        this.majorId = majorId;
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
        if (!(object instanceof BlockOfMajor)) {
            return false;
        }
        BlockOfMajor other = (BlockOfMajor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.BlockOfMajor[ id=" + id + " ]";
    }

}
