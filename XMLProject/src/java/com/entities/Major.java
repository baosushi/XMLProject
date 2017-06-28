package com.entities;
// Generated Jun 28, 2017 5:07:51 PM by Hibernate Tools 4.3.1


import java.io.Serializable;

/**
 * Major generated by hbm2java
 */
public class Major  implements java.io.Serializable {


     private int id;
     private MajorGroup majorGroup;
     private University university;
     private Integer mbtigroupId;
     private Serializable majorName;
     private Serializable majorCode;
     private Serializable alternativeCode;
     private Integer educationLevel;
     private Integer limit;
     private Integer examEntryQuantity;
     private Integer otherEntryQuantity;
     private Integer lastYearEntryQuantity;
     private Integer yearBeforeLastEntryQuantity;
     private Double gradeToPass;
     private Integer rate;
     private Integer fromGrade;
     private Serializable blockName;
     private Integer toGrade;
     private boolean active;
     private Serializable description;

    public Major() {
    }

	
    public Major(int id, Serializable majorName, boolean active) {
        this.id = id;
        this.majorName = majorName;
        this.active = active;
    }
    public Major(int id, MajorGroup majorGroup, University university, Integer mbtigroupId, Serializable majorName, Serializable majorCode, Serializable alternativeCode, Integer educationLevel, Integer limit, Integer examEntryQuantity, Integer otherEntryQuantity, Integer lastYearEntryQuantity, Integer yearBeforeLastEntryQuantity, Double gradeToPass, Integer rate, Integer fromGrade, Serializable blockName, Integer toGrade, boolean active, Serializable description) {
       this.id = id;
       this.majorGroup = majorGroup;
       this.university = university;
       this.mbtigroupId = mbtigroupId;
       this.majorName = majorName;
       this.majorCode = majorCode;
       this.alternativeCode = alternativeCode;
       this.educationLevel = educationLevel;
       this.limit = limit;
       this.examEntryQuantity = examEntryQuantity;
       this.otherEntryQuantity = otherEntryQuantity;
       this.lastYearEntryQuantity = lastYearEntryQuantity;
       this.yearBeforeLastEntryQuantity = yearBeforeLastEntryQuantity;
       this.gradeToPass = gradeToPass;
       this.rate = rate;
       this.fromGrade = fromGrade;
       this.blockName = blockName;
       this.toGrade = toGrade;
       this.active = active;
       this.description = description;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public MajorGroup getMajorGroup() {
        return this.majorGroup;
    }
    
    public void setMajorGroup(MajorGroup majorGroup) {
        this.majorGroup = majorGroup;
    }
    public University getUniversity() {
        return this.university;
    }
    
    public void setUniversity(University university) {
        this.university = university;
    }
    public Integer getMbtigroupId() {
        return this.mbtigroupId;
    }
    
    public void setMbtigroupId(Integer mbtigroupId) {
        this.mbtigroupId = mbtigroupId;
    }
    public Serializable getMajorName() {
        return this.majorName;
    }
    
    public void setMajorName(Serializable majorName) {
        this.majorName = majorName;
    }
    public Serializable getMajorCode() {
        return this.majorCode;
    }
    
    public void setMajorCode(Serializable majorCode) {
        this.majorCode = majorCode;
    }
    public Serializable getAlternativeCode() {
        return this.alternativeCode;
    }
    
    public void setAlternativeCode(Serializable alternativeCode) {
        this.alternativeCode = alternativeCode;
    }
    public Integer getEducationLevel() {
        return this.educationLevel;
    }
    
    public void setEducationLevel(Integer educationLevel) {
        this.educationLevel = educationLevel;
    }
    public Integer getLimit() {
        return this.limit;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Integer getExamEntryQuantity() {
        return this.examEntryQuantity;
    }
    
    public void setExamEntryQuantity(Integer examEntryQuantity) {
        this.examEntryQuantity = examEntryQuantity;
    }
    public Integer getOtherEntryQuantity() {
        return this.otherEntryQuantity;
    }
    
    public void setOtherEntryQuantity(Integer otherEntryQuantity) {
        this.otherEntryQuantity = otherEntryQuantity;
    }
    public Integer getLastYearEntryQuantity() {
        return this.lastYearEntryQuantity;
    }
    
    public void setLastYearEntryQuantity(Integer lastYearEntryQuantity) {
        this.lastYearEntryQuantity = lastYearEntryQuantity;
    }
    public Integer getYearBeforeLastEntryQuantity() {
        return this.yearBeforeLastEntryQuantity;
    }
    
    public void setYearBeforeLastEntryQuantity(Integer yearBeforeLastEntryQuantity) {
        this.yearBeforeLastEntryQuantity = yearBeforeLastEntryQuantity;
    }
    public Double getGradeToPass() {
        return this.gradeToPass;
    }
    
    public void setGradeToPass(Double gradeToPass) {
        this.gradeToPass = gradeToPass;
    }
    public Integer getRate() {
        return this.rate;
    }
    
    public void setRate(Integer rate) {
        this.rate = rate;
    }
    public Integer getFromGrade() {
        return this.fromGrade;
    }
    
    public void setFromGrade(Integer fromGrade) {
        this.fromGrade = fromGrade;
    }
    public Serializable getBlockName() {
        return this.blockName;
    }
    
    public void setBlockName(Serializable blockName) {
        this.blockName = blockName;
    }
    public Integer getToGrade() {
        return this.toGrade;
    }
    
    public void setToGrade(Integer toGrade) {
        this.toGrade = toGrade;
    }
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    public Serializable getDescription() {
        return this.description;
    }
    
    public void setDescription(Serializable description) {
        this.description = description;
    }




}


