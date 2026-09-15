package Model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private  String name;
    private  String description;
    private  String city;
    private  List<Tag>tags;

    public TouristAttraction(String name, String description, String city){
        this(name,description,city,List.of());
    }
    public TouristAttraction(String name, String description, String city, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    public TouristAttraction() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }
    public List<Tag> getTags() {
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addTag(Tag tag){
        tags.add(tag);
    }


}
