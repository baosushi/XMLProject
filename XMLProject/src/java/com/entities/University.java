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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "University")
@XmlRootElement
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
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "UniversityName")
    private String universityName;
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Column(name = "Email")
    private String email;
    @Column(name = "Website")
    private String website;
    @Column(name = "LogoUrl")
    private String logoUrl;
    @Basic(optional = false)
    @Column(name = "Priority")
    private int priority;
    @Column(name = "Description")
    private String description;
    @Column(name = "Active")
    private Boolean active;
    @Column(name = "Code")
    private String code;
    @Column(name = "EducationLevel")
    private Integer educationLevel;
    @OneToMany(mappedBy = "universityId")
    private List<Major> majorList;
    @JoinColumn(name = "LocationId", referencedColumnName = "ID")
    @ManyToOne
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
