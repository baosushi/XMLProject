
package jaxb.classes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listUniversity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listUniversity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="university" type="{http://xml.netbeans.org/schema/university}universityItem" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listUniversity", propOrder = {
    "university"
})
public class ListUniversity {

    @XmlElement(required = true)
    protected List<UniversityItem> university;

    /**
     * Gets the value of the university property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the university property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUniversity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UniversityItem }
     * 
     * 
     */
    public List<UniversityItem> getUniversity() {
        if (university == null) {
            university = new ArrayList<UniversityItem>();
        }
        return this.university;
    }

    public UniversityItem getUniversityItemByCode(String code) {
        for (UniversityItem each : university) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }
}
