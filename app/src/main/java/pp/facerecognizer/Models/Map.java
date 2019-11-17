package pp.facerecognizer.Models;

public class Map {
    private String name,image;

    public Map(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Map() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
