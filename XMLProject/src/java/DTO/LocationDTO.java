package DTO;

public class LocationDTO {

    private Integer id;
    private String locationName;
    private Integer priority;

    public LocationDTO() {
    }

    public LocationDTO(int id, String locationName, int priority) {
        this.id = id;
        this.locationName = locationName;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
