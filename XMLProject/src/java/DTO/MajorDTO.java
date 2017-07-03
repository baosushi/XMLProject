package DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "universityId",
    "name",
    "code",
    "limit",
    "examEntryQuantity",
    "otherEntryQuantity",
    "lastYearEntryQuantity",
    "yearBeforeLastEntryQuantity",
    "acitve"})
public class MajorDTO {

    @XmlElement(required = true)
    private int id;
    @XmlElement(required = true)
    private int universityId;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String code;
    @XmlElement(required = true)
    private int limit;
    @XmlElement(required = true)
    private int examEntryQuantity;
    @XmlElement(required = true)
    private int otherEntryQuantity;
    @XmlElement(required = true)
    private int lastYearEntryQuantity;
    @XmlElement(required = true)
    private int yearBeforeLastEntryQuantity;
    @XmlElement(required = true)
    private boolean acitve;

    public MajorDTO() {
    }

    public MajorDTO(int id, int universityId, String name, String code, int limit, int examEntryQuantity, int otherEntryQuantity, int lastYearEntryQuantity, int yearBeforeLastEntryQuantity, boolean acitve) {
        this.id = id;
        this.universityId = universityId;
        this.name = name;
        this.code = code;
        this.limit = limit;
        this.examEntryQuantity = examEntryQuantity;
        this.otherEntryQuantity = otherEntryQuantity;
        this.lastYearEntryQuantity = lastYearEntryQuantity;
        this.yearBeforeLastEntryQuantity = yearBeforeLastEntryQuantity;
        this.acitve = acitve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getExamEntryQuantity() {
        return examEntryQuantity;
    }

    public void setExamEntryQuantity(int examEntryQuantity) {
        this.examEntryQuantity = examEntryQuantity;
    }

    public int getOtherEntryQuantity() {
        return otherEntryQuantity;
    }

    public void setOtherEntryQuantity(int otherEntryQuantity) {
        this.otherEntryQuantity = otherEntryQuantity;
    }

    public int getLastYearEntryQuantity() {
        return lastYearEntryQuantity;
    }

    public void setLastYearEntryQuantity(int lastYearEntryQuantity) {
        this.lastYearEntryQuantity = lastYearEntryQuantity;
    }

    public int getYearBeforeLastEntryQuantity() {
        return yearBeforeLastEntryQuantity;
    }

    public void setYearBeforeLastEntryQuantity(int yearBeforeLastEntryQuantity) {
        this.yearBeforeLastEntryQuantity = yearBeforeLastEntryQuantity;
    }

    public boolean isAcitve() {
        return acitve;
    }

    public void setAcitve(boolean acitve) {
        this.acitve = acitve;
    }

}
