package Model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private final String name;
    private final String description;
    private final String city;
    private final List<Tag>tags;

    public TouristAttraction(String name, String description, String ciry){
        this(name,description,ciry,List.of());
    }
    public TouristAttraction(String name, String description, String ciry, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.city = ciry;
        this.tags = tags;
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

    public void addTag(Tag tag){
        tags.add(tag);
    }
}
