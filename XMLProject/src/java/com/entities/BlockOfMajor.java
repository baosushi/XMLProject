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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Temporary
 */
@Entity
@Table(name = "BlockOfMajor")
@XmlRootElement
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
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_JUST_FOR_TEST", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MainSubjectCode")
    private String mainSubjectCode;
    @Column(name = "AcceptedEntryLastYear")
    private Integer acceptedEntryLastYear;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BaseScoreLastYear")
    private Double baseScoreLastYear;
    @Column(name = "AcceptedEntryYearBeforeLast")
    private Integer acceptedEntryYearBeforeLast;
    @Column(name = "BaseScoreYearBeforeLast")
    private Double baseScoreYearBeforeLast;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Active")
    private boolean active;
    @JoinColumn(name = "BlockId", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Block blockId;
    @JoinColumn(name = "MajorId", referencedColumnName = "ID")
    @ManyToOne(optional = false)
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
