
package jaxb.classes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for major complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="major">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="block" type="{http://xml.netbeans.org/schema/university}blockItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="baseScore" type="{http://xml.netbeans.org/schema/university}score" minOccurs="0"/>
 *         &lt;element name="acceptEntry" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "major", propOrder = {
    "block",
    "baseScore",
    "acceptEntry"
})
public class Major {

    protected List<BlockItem> block;
    protected BigDecimal baseScore;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger acceptEntry;

    /**
     * Gets the value of the block property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the block property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlock().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlockItem }
     * 
     * 
     */
    public List<BlockItem> getBlock() {
        if (block == null) {
            block = new ArrayList<BlockItem>();
        }
        return this.block;
    }

    /**
     * Gets the value of the baseScore property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBaseScore() {
        return baseScore;
    }

    /**
     * Sets the value of the baseScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBaseScore(BigDecimal value) {
        this.baseScore = value;
    }

    /**
     * Gets the value of the acceptEntry property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAcceptEntry() {
        return acceptEntry;
    }

    /**
     * Sets the value of the acceptEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAcceptEntry(BigInteger value) {
        this.acceptEntry = value;
    }

}
