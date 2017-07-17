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
@Table(name = "University")
@XmlRootElement(name = "university")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "universityName",
    "phoneNumber",
    "email",
    "website",
    "logoUrl",
    "priority",
    "description",
    "active",
    "code",
    "educationLevel",
    "majorList"
})
@NamedQueries({
    @NamedQuery(name = "University.findAll", query = "SELECT u FROM University u"),
    @NamedQuery(name = "University.findById", query = "SELECT u FROM University u WHERE u.id = :id"),
    @NamedQuery(name = "University.findByUniversityName", query = "SELECT u FROM University u WHERE u.universityName = :universityName"),
    @NamedQuery(name = "University.findByPhoneNumber", query = "SELECT u FROM University u WHERE u.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "University.findByEmail", query = "SELECT u FROM University u WHERE u.email = :email"),
    @NamedQuery(name = "University.findByWebsite", query = "SELECT u FROM University u WHERE u.website = :website"),
    @NamedQuery(name = "University.findByLogoUrl", query = "SELECT u FROM University u WHERE u.logoUrl = :logoUrl"),
    @NamedQuery(name = "University.findByPriority", query = "SELECT u FROM University u WHERE u.priority = :priority"),
    @NamedQuery(name = "University.findByDescription", query = "SELECT u FROM University u WHERE u.description = :description"),
    @NamedQuery(name = "University.findByActive", query = "SELECT u FROM University u WHERE u.active = :active"),
    @NamedQuery(name = "University.findByCode", query = "SELECT u FROM University u WHERE u.code = :code"),
    @NamedQuery(name = "University.findByEducationLevel", query = "SELECT u FROM University u WHERE u.educationLevel = :educationLevel")})
public class University implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_JUST_FOR_TEST", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Basic(optional = false)
    @Column(name = "ID")
    @XmlElement(required = true)
    private Integer id;
    @Column(name = "UniversityName")
    @XmlElement(required = true)
    private String universityName;
    @Column(name = "PhoneNumber")
    @XmlElement(required = true)
    private String phoneNumber;
    @Column(name = "Email")
    @XmlElement(required = true)
    private String email;
    @Column(name = "Website")
    @XmlElement(required = true)
    private String website;
    @Column(name = "LogoUrl")
    @XmlElement(required = true)
    private String logoUrl;
    @Basic(optional = false)
    @Column(name = "Priority")
    @XmlElement(required = true)
    private int priority;
    @Column(name = "Description")
    @XmlElement(required = true)
    private String description;
    @Column(name = "Active")
    @XmlElement(required = true)
    private Boolean active;
    @Column(name = "Code")
    @XmlElement(required = true)
    private String code;
    @Column(name = "EducationLevel")
    @XmlElement(required = true)
    private Integer educationLevel;
    @OneToMany(mappedBy = "universityId")
    @XmlElementWrapper(name = "majors")
    @XmlElement(name="major", required = true)
    private List<Major> majorList;
    @JoinColumn(name = "LocationId", referencedColumnName = "ID")
    @ManyToOne
    @XmlTransient
    private Location locationId;

    public University() {
    }

    public University(Integer id) {
        this.id = id;
    }

    public University(Integer id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Integer educationLevel) {
        this.educationLevel = educationLevel;
    }

    @XmlTransient
    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
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
        if (!(object instanceof University)) {
            return false;
        }
        University other = (University) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.University[ id=" + id + " ]";
    }
    
}
