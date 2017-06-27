package DTO;

public class SubjectDTO {

    private Integer id;
    private String subjectName;
    private String description;
    private boolean active;

    public SubjectDTO() {
    }

    public SubjectDTO(int id, String subjectName, String description, boolean active) {
        this.id = id;
        this.subjectName = subjectName;
        this.description = description;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
}
