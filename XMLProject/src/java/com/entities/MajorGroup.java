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
@Table(name = "MajorGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MajorGroup.findAll", query = "SELECT m FROM MajorGroup m"),
    @NamedQuery(name = "MajorGroup.findById", query = "SELECT m FROM MajorGroup m WHERE m.id = :id"),
    @NamedQuery(name = "MajorGroup.findByMajorTypeId", query = "SELECT m FROM MajorGroup m WHERE m.majorTypeId = :majorTypeId"),
    @NamedQuery(name = "MajorGroup.findByMajorGroupName", query = "SELECT m FROM MajorGroup m WHERE m.majorGroupName = :majorGroupName"),
    @NamedQuery(name = "MajorGroup.findByDisplayOrder", query = "SELECT m FROM MajorGroup m WHERE m.displayOrder = :displayOrder"),
    @NamedQuery(name = "MajorGroup.findByMajorCodePrefix1", query = "SELECT m FROM MajorGroup m WHERE m.majorCodePrefix1 = :majorCodePrefix1"),
    @NamedQuery(name = "MajorGroup.findByMajorCodePrefix2", query = "SELECT m FROM MajorGroup m WHERE m.majorCodePrefix2 = :majorCodePrefix2"),
    @NamedQuery(name = "MajorGroup.findByMajorCodePrefix3", query = "SELECT m FROM MajorGroup m WHERE m.majorCodePrefix3 = :majorCodePrefix3"),
    @NamedQuery(name = "MajorGroup.findByMajorCodePrefix4", query = "SELECT m FROM MajorGroup m WHERE m.majorCodePrefix4 = :majorCodePrefix4"),
    @NamedQuery(name = "MajorGroup.findByDescription", query = "SELECT m FROM MajorGroup m WHERE m.description = :description"),
    @NamedQuery(name = "MajorGroup.findByActive", query = "SELECT m FROM MajorGroup m WHERE m.active = :active")})
public class MajorGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MajorTypeId")
    private Integer majorTypeId;
    @Column(name = "MajorGroupName")
    private String majorGroupName;
    @Column(name = "DisplayOrder")
    private Integer displayOrder;
    @Column(name = "MajorCodePrefix1")
    private String majorCodePrefix1;
    @Column(name = "MajorCodePrefix2")
    private String majorCodePrefix2;
    @Column(name = "MajorCodePrefix3")
    private String majorCodePrefix3;
    @Column(name = "MajorCodePrefix4")
    private String majorCodePrefix4;
    @Column(name = "Description")
    private String description;
    @Column(name = "Active")
    private Boolean active;
    @OneToMany(mappedBy = "majorGroupId")
    private List<Major> majorList;

    public MajorGroup() {
    }

    public MajorGroup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(Integer majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    public String getMajorGroupName() {
        return majorGroupName;
    }

    public void setMajorGroupName(String majorGroupName) {
        this.majorGroupName = majorGroupName;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getMajorCodePrefix1() {
        return majorCodePrefix1;
    }

    public void setMajorCodePrefix1(String majorCodePrefix1) {
        this.majorCodePrefix1 = majorCodePrefix1;
    }

    public String getMajorCodePrefix2() {
        return majorCodePrefix2;
    }

    public void setMajorCodePrefix2(String majorCodePrefix2) {
        this.majorCodePrefix2 = majorCodePrefix2;
    }

    public String getMajorCodePrefix3() {
        return majorCodePrefix3;
    }

    public void setMajorCodePrefix3(String majorCodePrefix3) {
        this.majorCodePrefix3 = majorCodePrefix3;
    }

    public String getMajorCodePrefix4() {
        return majorCodePrefix4;
    }

    public void setMajorCodePrefix4(String majorCodePrefix4) {
        this.majorCodePrefix4 = majorCodePrefix4;
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

    @XmlTransient
    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
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
        if (!(object instanceof MajorGroup)) {
            return false;
        }
        MajorGroup other = (MajorGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.MajorGroup[ id=" + id + " ]";
    }
    
}
