package repository;

import Model.Tag;
import Model.TouristAttraction;
import org.springframework.stereotype.Repository;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {
        populate();
    }

    public TouristAttraction updateTouristAttraction(String name, TouristAttraction updatedTouristAttraction) {
        for (TouristAttraction touristAttraction : touristAttractions) {
            if (touristAttraction.getName().equals(name)) {
                int index = touristAttractions.indexOf(touristAttraction);
                touristAttractions.set(index, updatedTouristAttraction);
                return updatedTouristAttraction;
            }
        }
        return null;
    }

    public boolean deleteTouristAttraction(String name) {
        TouristAttraction existing = findByName(name);
        if (existing == null) {
            return false;
        }
        touristAttractions.remove(existing);
        return true;
    }

    public TouristAttraction AddTagToTouristattraction(String name, Tag tag) {
        for (TouristAttraction touristAttraction : touristAttractions)
            if (touristAttraction.getName().equalsIgnoreCase(name)) {
                touristAttraction.addTag(tag);
                return touristAttraction;
            }
        return null;
    }

    public TouristAttraction save(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
        return touristAttraction;
    }

    public TouristAttraction findByName(String name) {
        for (TouristAttraction touristAttraction : touristAttractions)
            if (touristAttraction.getName().equalsIgnoreCase(name)) {
                return touristAttraction;
            }
        return null;
    }

    public List<TouristAttraction> findTouristAttractionsByDescription(String description) {
        List<TouristAttraction> touristAttraction = new ArrayList<>();
        for (TouristAttraction touristAttraction1 : touristAttractions) {
            if (touristAttraction1.getDescription().contains(description)) {
                touristAttraction.add(touristAttraction1);
            }
        }
        return touristAttraction;
    }

    public void populate() {
        touristAttractions.add(new TouristAttraction("Tivoli", "19th-century amusement park with antique roller coaster & live entertainment program.","Copenhagen",List.of(Tag.KID_FRIENDLY, Tag.NATURE)));
        touristAttractions.add(new TouristAttraction("Rosenborg Castle", "Dutch Renaissance palace & gardens, including a museum housing the crown jewels, with guided tours.","Copenhagen",List.of(Tag.MUSEUM, Tag.NATURE)));
        touristAttractions.add(new TouristAttraction("Frederiksborg Castle", "Dutch Renaissance palace & gardens, including a museum housing the crown jewels, with guided tours.","Copenhagen",List.of(Tag.MUSEUM, Tag.NATURE)));
        touristAttractions.add(new TouristAttraction("Nyhavn", "Copenhagen's Nyhaven, or \"New Harbor,\" is actually steeped in a long heritage. Colorful buildings line the canal and hint at a history of small-vessel traffic. ","Copenhagen",List.of(Tag.FREE, Tag.NATURE)));
        touristAttractions.add(new TouristAttraction("The Little Mermaid", "Bronze statue by Edvard Eriksen depicting a mermaid, based on the fairy tale by Hans Christian Andersen.","Copenhagen",List.of(Tag.FREE, Tag.ART)));
        touristAttractions.add(new TouristAttraction("Christiansborg Palace", "Seat of the Danish Parliament, the Supreme Court, and the Prime Minister's office, with a royal reception hall.","Copenhagen",List.of(Tag.MUSEUM)));
        touristAttractions.add(new TouristAttraction("The Round Tower", "17th-century tower with a spiral ramp leading to an observatory offering panoramic views of Copenhagen.","Copenhagen",List.of(Tag.NATURE)));
        touristAttractions.add(new TouristAttraction("Amalienborg Palace", "Winter home of the Danish royal family, consisting of four identical palace buildings around an octagonal courtyard.","Copenhagen",List.of(Tag.FREE)));
    }

    public List<TouristAttraction> getTouristAttractions() {
        return touristAttractions;
    }

    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        for (TouristAttraction a : touristAttractions) {
            if (!cities.contains(a.getCity())) cities.add(a.getCity());
        }
        return cities;
    }
    public List<String> getTags() {
        List<String> tags = new ArrayList<>();
        for (TouristAttraction a : touristAttractions) {
            for (Tag tag : a.getTags()) {
                if (!tags.contains(tag.toString())) tags.add(tag.toString());
            }
        }
        return tags;
    }
}
