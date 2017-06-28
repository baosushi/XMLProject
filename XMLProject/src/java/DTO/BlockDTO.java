package DTO;

public class BlockDTO {

    private Integer id;
    private String blockName;
    private String description;
    private boolean active;

    public BlockDTO() {
    }

    public BlockDTO(int id, String blockName, String description, boolean active) {
        this.id = id;
        this.blockName = blockName;
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
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
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
