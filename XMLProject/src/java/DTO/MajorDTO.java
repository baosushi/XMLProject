package DTO;

import BLO.UniversityBLO;
import com.entities.BlockOfMajor;
import com.entities.Major;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "code",
    "limit",
    "examEntryQuantity",
    "otherEntryQuantity",
    "lastYearEntryQuantity",
    "yearBeforeLastEntryQuantity",
    "gradeToPass",
    "university",
    "active"})
public class MajorDTO {

    @XmlElement(required = true)
    private int id;
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
    private double gradeToPass;
    @XmlElement(required = true)
    private UniversityDTO university;
    @XmlElement(required = true)
    private boolean active;
//    @XmlElement(required = true)
//    private List<BlockOfMajorDTO> blockOfMajorList;

    public MajorDTO() {
    }

    public MajorDTO(int id, int universityId, String name, String code, int limit, int examEntryQuantity, int otherEntryQuantity, int lastYearEntryQuantity, int yearBeforeLastEntryQuantity, boolean acitve) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.limit = limit;
        this.examEntryQuantity = examEntryQuantity;
        this.otherEntryQuantity = otherEntryQuantity;
        this.lastYearEntryQuantity = lastYearEntryQuantity;
        this.yearBeforeLastEntryQuantity = yearBeforeLastEntryQuantity;
        this.active = acitve;

        UniversityBLO blo = new UniversityBLO();
        this.university = blo.findById(universityId);
    }

    public MajorDTO(Major m) {
        this.id = m.getId();
        this.university = new UniversityDTO(m.getUniversityId());
        this.name = m.getMajorName();
        this.code = m.getMajorCode();
        this.limit = m.getLimit();
        this.examEntryQuantity = m.getExamEntryQuantity();
        this.otherEntryQuantity = m.getOtherEntryQuantity();
        this.lastYearEntryQuantity = m.getLastYearEntryQuantity();
        this.yearBeforeLastEntryQuantity = m.getYearBeforeLastEntryQuantity();
        this.active = m.getActive();

//        if (this.blockOfMajorList == null) {
//            this.blockOfMajorList = new ArrayList<BlockOfMajorDTO>();
//        }
//
//        for (BlockOfMajor b : m.getBlockOfMajorList()) {
//            this.blockOfMajorList.add(new BlockOfMajorDTO(b));
//        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UniversityDTO getUniversity() {
        return university;
    }

    public void setUniversityId(UniversityDTO university) {
        this.university = university;
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
        return active;
    }

    public void setAcitve(boolean acitve) {
        this.active = acitve;
    }

    public double getGradeToPass() {
        return gradeToPass;
    }

    public void setGradeToPass(double gradeToPass) {
        this.gradeToPass = gradeToPass;
    }

}
