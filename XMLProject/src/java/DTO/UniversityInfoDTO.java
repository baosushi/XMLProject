/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import com.entities.*;
import java.util.List;
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "universityInfo")
@XmlType(name = "", propOrder = {
    "university",
    "majors"})
public class UniversityInfoDTO {

    public UniversityInfoDTO(University university) {
        this.university = university;
        this.majors = this.university.getMajorList();
    }

    public UniversityInfoDTO() {
    }

    @XmlElement(required = true)
    private University university;
    @XmlElementWrapper(name = "majors")
    @XmlElement(name="major", required = true)
    private List<Major> majors;

    @XmlTransient
    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @XmlTransient
    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }

}
