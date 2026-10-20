package repository;

import Model.AttractionRowMapper;
import Model.Tag;
import Model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private final JdbcTemplate jdbcTemplate;

    // Spring giver JdbcTemplate med url, brugernavn og password fra application-dev.properties
    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Laver en liste af tags om til tekst "KID_FRIENDLY,NATURE"

    private String tagsToString(List<Tag> tags) {
        String text = "";
        for (Tag tag : tags) {
            text = text + tag.name() + ",";
        }
        if (text.endsWith(",")) {
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    // CREATE: gemmer en ny attraktion
    public TouristAttraction save(TouristAttraction a) {
        String sql = "INSERT INTO attraction (name, description, city, tags) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, a.getName(), a.getDescription(), a.getCity(), tagsToString(a.getTags()));
        return a;
    }

    // READ: henter alle attraktioner
    public List<TouristAttraction> getTouristAttractions() {
        String sql = "SELECT * FROM attraction";
        return jdbcTemplate.query(sql, new AttractionRowMapper());
    }

    // READ: finder en attraktion ud fra navn
    public TouristAttraction findByName(String name) {
        String sql = "SELECT * FROM attraction WHERE name = ?";
        List<TouristAttraction> list = jdbcTemplate.query(sql, new AttractionRowMapper(), name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // READ: finder attraktioner hvor beskrivelsen indeholder teksten
    public List<TouristAttraction> findTouristAttractionsByDescription(String description) {
        String sql = "SELECT * FROM attraction WHERE description LIKE ?";
        return jdbcTemplate.query(sql, new AttractionRowMapper(), "." + description + ".");
    }

    // UPDATE: opdaterer en attraktion ud fra navn

    public TouristAttraction updateTouristAttraction(String name, TouristAttraction updated) {
        String sql = "UPDATE attraction SET name = ?, description = ?, city = ?, tags = ? WHERE name = ?";
        int rows = jdbcTemplate.update(sql, updated.getName(), updated.getDescription(), updated.getCity(), tagsToString(updated.getTags()), name);
        if (rows > 0) {
            return updated;
        }
        return null;
    }

    // UPDATE: tilføjer et tag til en attraktion

    public TouristAttraction AddTagToTouristattraction(String name, Tag tag) {
        TouristAttraction a = findByName(name);
        if (a == null) {
            return null;
        }
        a.addTag(tag);
        return updateTouristAttraction(name, a);
    }

    // DELETE: sletter en attraktion ud fra navn
    public boolean deleteTouristAttraction(String name) {
        String sql = "DELETE FROM attraction WHERE name = ?";
        return jdbcTemplate.update(sql, name) > 0;
    }

    // cities uden dubletter

    public List<String> getCities() {
        String sql = "SELECT DISTINCT city FROM attraction";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    // tags uden dubletter

    public List<String> getTags() {
        List<String> tags = new ArrayList<>();
        for (TouristAttraction a : getTouristAttractions()) {
            for (Tag tag : a.getTags()) {
                if (!tags.contains(tag.toString())) {
                    tags.add(tag.toString());
                }
            }
        }
        return tags;
    }
}
