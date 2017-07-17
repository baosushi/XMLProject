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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Temporary
 */
@Entity
@Table(name = "Major")
@XmlRootElement(name = "major")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "majorName",
    "majorCode",
    "limit",
    "examEntryQuantity",
    "otherEntryQuantity",
    "lastYearEntryQuantity",
    "yearBeforeLastEntryQuantity",
    "gradeToPass",
    "active",
    "blockOfMajorList"})
@NamedQueries({
    @NamedQuery(name = "Major.findAll", query = "SELECT m FROM Major m"),
    @NamedQuery(name = "Major.findById", query = "SELECT m FROM Major m WHERE m.id = :id"),
    @NamedQuery(name = "Major.findByMBTIGroupId", query = "SELECT m FROM Major m WHERE m.mBTIGroupId = :mBTIGroupId"),
    @NamedQuery(name = "Major.findByMajorName", query = "SELECT m FROM Major m WHERE m.majorName = :majorName"),
    @NamedQuery(name = "Major.findByMajorCode", query = "SELECT m FROM Major m WHERE m.majorCode = :majorCode"),
    @NamedQuery(name = "Major.findByAlternativeCode", query = "SELECT m FROM Major m WHERE m.alternativeCode = :alternativeCode"),
    @NamedQuery(name = "Major.findByEducationLevel", query = "SELECT m FROM Major m WHERE m.educationLevel = :educationLevel"),
    @NamedQuery(name = "Major.findByLimit", query = "SELECT m FROM Major m WHERE m.limit = :limit"),
    @NamedQuery(name = "Major.findByExamEntryQuantity", query = "SELECT m FROM Major m WHERE m.examEntryQuantity = :examEntryQuantity"),
    @NamedQuery(name = "Major.findByOtherEntryQuantity", query = "SELECT m FROM Major m WHERE m.otherEntryQuantity = :otherEntryQuantity"),
    @NamedQuery(name = "Major.findByLastYearEntryQuantity", query = "SELECT m FROM Major m WHERE m.lastYearEntryQuantity = :lastYearEntryQuantity"),
    @NamedQuery(name = "Major.findByYearBeforeLastEntryQuantity", query = "SELECT m FROM Major m WHERE m.yearBeforeLastEntryQuantity = :yearBeforeLastEntryQuantity"),
    @NamedQuery(name = "Major.findByGradeToPass", query = "SELECT m FROM Major m WHERE m.gradeToPass = :gradeToPass"),
    @NamedQuery(name = "Major.findByRate", query = "SELECT m FROM Major m WHERE m.rate = :rate"),
    @NamedQuery(name = "Major.findByFromGrade", query = "SELECT m FROM Major m WHERE m.fromGrade = :fromGrade"),
    @NamedQuery(name = "Major.findByBlockName", query = "SELECT m FROM Major m WHERE m.blockName = :blockName"),
    @NamedQuery(name = "Major.findByToGrade", query = "SELECT m FROM Major m WHERE m.toGrade = :toGrade"),
    @NamedQuery(name = "Major.findByActive", query = "SELECT m FROM Major m WHERE m.active = :active"),
    @NamedQuery(name = "Major.findByDescription", query = "SELECT m FROM Major m WHERE m.description = :description")})
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_JUST_FOR_TEST", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Basic(optional = false)
    @Column(name = "ID")
    @XmlElement(required = true)
    private Integer id;
    @Column(name = "MBTIGroupId")
    @XmlTransient
    private Integer mBTIGroupId;
    @Basic(optional = false)
    @Column(name = "MajorName")
    @XmlElement(required = true)
    private String majorName;
    @Column(name = "MajorCode")
    @XmlElement(required = true)
    private String majorCode;
    @Column(name = "AlternativeCode")
    @XmlTransient
    private String alternativeCode;
    @Column(name = "EducationLevel")
    @XmlTransient
    private Integer educationLevel;
    @Column(name = "Limit")
    @XmlElement(required = true)
    private Integer limit;
    @Column(name = "ExamEntryQuantity")
    @XmlElement(required = false)
    private Integer examEntryQuantity;
    @Column(name = "OtherEntryQuantity")
    @XmlElement(required = true)
    private Integer otherEntryQuantity;
    @Column(name = "LastYearEntryQuantity")
    @XmlElement(required = true)
    private Integer lastYearEntryQuantity;
    @Column(name = "YearBeforeLastEntryQuantity")
    @XmlElement(required = false)
    private Integer yearBeforeLastEntryQuantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GradeToPass")
    @XmlElement(required = true)
    private Double gradeToPass;
    @Column(name = "Rate")
    @XmlTransient
    private Integer rate;
    @Column(name = "FromGrade")
    @XmlTransient
    private Integer fromGrade;
    @Column(name = "BlockName")
    @XmlTransient
    private String blockName;
    @Column(name = "ToGrade")
    @XmlTransient
    private Integer toGrade;
    @Basic(optional = false)
    @Column(name = "Active")
    @XmlElement(required = true)
    private boolean active;
    @Column(name = "Description")
    @XmlTransient
    private String description;
    @JoinColumn(name = "MajorGroupId", referencedColumnName = "ID")
    @ManyToOne
    @XmlTransient
    private MajorGroup majorGroupId;
    @JoinColumn(name = "UniversityId", referencedColumnName = "ID")
    @ManyToOne
    @XmlTransient
    private University universityId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "majorId")
    @XmlElementWrapper(name = "blockOfMajors")
    @XmlElement(name="blockOfMajor", required = true)
    private List<BlockOfMajor> blockOfMajorList;

    public Major() {
    }

    public Major(Integer id) {
        this.id = id;
    }

    public Major(Integer id, String majorName, boolean active) {
        this.id = id;
        this.majorName = majorName;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMBTIGroupId() {
        return mBTIGroupId;
    }

    public void setMBTIGroupId(Integer mBTIGroupId) {
        this.mBTIGroupId = mBTIGroupId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getAlternativeCode() {
        return alternativeCode;
    }

    public void setAlternativeCode(String alternativeCode) {
        this.alternativeCode = alternativeCode;
    }

    public Integer getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Integer educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getExamEntryQuantity() {
        return examEntryQuantity;
    }

    public void setExamEntryQuantity(Integer examEntryQuantity) {
        this.examEntryQuantity = examEntryQuantity;
    }

    public Integer getOtherEntryQuantity() {
        return otherEntryQuantity;
    }

    public void setOtherEntryQuantity(Integer otherEntryQuantity) {
        this.otherEntryQuantity = otherEntryQuantity;
    }

    public Integer getLastYearEntryQuantity() {
        return lastYearEntryQuantity;
    }

    public void setLastYearEntryQuantity(Integer lastYearEntryQuantity) {
        this.lastYearEntryQuantity = lastYearEntryQuantity;
    }

    public Integer getYearBeforeLastEntryQuantity() {
        return yearBeforeLastEntryQuantity;
    }

    public void setYearBeforeLastEntryQuantity(Integer yearBeforeLastEntryQuantity) {
        this.yearBeforeLastEntryQuantity = yearBeforeLastEntryQuantity;
    }

    public Double getGradeToPass() {
        return gradeToPass;
    }

    public void setGradeToPass(Double gradeToPass) {
        this.gradeToPass = gradeToPass;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getFromGrade() {
        return fromGrade;
    }

    public void setFromGrade(Integer fromGrade) {
        this.fromGrade = fromGrade;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Integer getToGrade() {
        return toGrade;
    }

    public void setToGrade(Integer toGrade) {
        this.toGrade = toGrade;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MajorGroup getMajorGroupId() {
        return majorGroupId;
    }

    public void setMajorGroupId(MajorGroup majorGroupId) {
        this.majorGroupId = majorGroupId;
    }

    public University getUniversityId() {
        return universityId;
    }

    public void setUniversityId(University universityId) {
        this.universityId = universityId;
    }

    @XmlTransient
    public List<BlockOfMajor> getBlockOfMajorList() {
        return blockOfMajorList;
    }

    public void setBlockOfMajorList(List<BlockOfMajor> blockOfMajorList) {
        this.blockOfMajorList = blockOfMajorList;
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
        if (!(object instanceof Major)) {
            return false;
        }
        Major other = (Major) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Major[ id=" + id + " ]";
    }

}
