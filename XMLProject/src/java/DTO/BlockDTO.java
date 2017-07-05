package DTO;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "description",
    "active"})
public class BlockDTO implements Serializable {

    @XmlElement(required = true)
    private Integer id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = false)
    private String description;
    @XmlElement(required = true)
    private boolean active;
    @XmlElement(required = true)
    private List<BlockOfMajorDTO> blockOfMajorList;

    public BlockDTO() {
    }

    public BlockDTO(int id, String name, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlockName() {
        return name;
    }

    public void setBlockName(String name) {
        this.name = name;
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
