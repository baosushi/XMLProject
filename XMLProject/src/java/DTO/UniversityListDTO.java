/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Temporary
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "universityDTO", propOrder = {
    "university"
})
@XmlRootElement(name = "universityListDTO")
public class UniversityListDTO implements Serializable {

    @XmlElement(required = true)
    protected List<UniversityDTO> university;

    public List<UniversityDTO> getUniversity() {
        if (university == null) {
            university = new ArrayList<UniversityDTO>();
        }
        return university;
    }

    public void setUniversity(List<UniversityDTO> university) {
        this.university = university;
    }
}
