package DTO;

import com.entities.University;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "universityName",
    "locationId",
    "phoneNumber",
    "email",
    "website",
    "logoUrl",
    "priority",
    "description",
    "active",
    "code",
    "educationLevel"
})
public class UniversityDTO implements Serializable {

    @XmlElement(required = true)
    private Integer id;
    @XmlElement(required = true)
    private String universityName;
    @XmlElement(required = true)
    private Integer locationId;
    @XmlElement(required = true)
    private String phoneNumber;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String website;
    @XmlElement(required = true)
    private String logoUrl;
    @XmlElement(required = true)
    private Integer priority;
    @XmlElement(required = true)
    private String description;
    @XmlElement(required = true)
    private boolean active;
    @XmlElement(required = true)
    private String code;
    @XmlElement(required = true)
    private Integer educationLevel;

    public UniversityDTO() {
    }

    public UniversityDTO(int id, String universityName, int locationId, String phoneNumber, String email, String website, String logoUrl, int priority, String description, boolean active, String code, int educationLevel) {
        this.id = id;
        this.universityName = universityName;
        this.locationId = locationId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
        this.logoUrl = logoUrl;
        this.priority = priority;
        this.description = description;
        this.active = active;
        this.code = code;
        this.educationLevel = educationLevel;
    }
    
    public UniversityDTO(University entity) {
        this.id = entity.getId();
        this.universityName = entity.getUniversityName();
        this.locationId = 0; //hardcode to test
        this.phoneNumber = entity.getPhoneNumber();
        this.email = entity.getEmail();
        this.website = entity.getWebsite();
        this.logoUrl = entity.getLogoUrl();
        this.priority = entity.getPriority();
        this.description = entity.getDescription();
        this.active = entity.getActive();
        this.code = entity.getCode();
        this.educationLevel = entity.getEducationLevel();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }
}
