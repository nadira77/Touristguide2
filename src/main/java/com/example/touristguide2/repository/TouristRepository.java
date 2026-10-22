
package com.example.touristguide2.repository;

import com.example.touristguide2.model.AttractionRowMapper;
import com.example.touristguide2.model.Tag;
import com.example.touristguide2.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TouristRepository {

    private final JdbcTemplate jdbcTemplate;

    // Spring giver JdbcTemplate med url, brugernavn og password fra application-dev.properties
    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE: gemmer en ny attraktion
    public TouristAttraction save(TouristAttraction a) {
        String sql = "INSERT INTO attraction (attraction_name, description, city_name) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, a.getName(), a.getDescription(), a.getCity());

        Integer attractionId = jdbcTemplate.queryForObject(
                "SELECT attraction_id FROM attraction WHERE attraction_name = ?",
                Integer.class,
                a.getName()
        );

        for (Tag tag : a.getTags()) {
            Integer tagId = jdbcTemplate.queryForObject(
                    "SELECT tag_id FROM tag WHERE tag_name = ?",
                    Integer.class,
                    tag.name()
            );

            jdbcTemplate.update(
                    "INSERT INTO attraction_tag (attraction_id, tag_id) VALUES (?, ?)",
                    attractionId, tagId
            );
        }

        return a;
    }

    // READ: henter alle attraktioner med deres tags
    public List<TouristAttraction> getTouristAttractions() {
        String sql = """
                SELECT a.attraction_id, a.attraction_name, a.description,
                       a.city_name, GROUP_CONCAT(t.tag_name) AS tags
                FROM attraction a
                LEFT JOIN attraction_tag at ON a.attraction_id = at.attraction_id
                LEFT JOIN tag t ON at.tag_id = t.tag_id
                GROUP BY a.attraction_id, a.attraction_name,
                         a.description, a.city_name
                """;

        return jdbcTemplate.query(sql, new AttractionRowMapper());
    }

    // READ: finder en attraktion ud fra navn
    public TouristAttraction findByName(String name) {
        String sql = """
                SELECT a.attraction_id, a.attraction_name, a.description,
                       a.city_name, GROUP_CONCAT(t.tag_name) AS tags
                FROM attraction a
                LEFT JOIN attraction_tag at ON a.attraction_id = at.attraction_id
                LEFT JOIN tag t ON at.tag_id = t.tag_id
                WHERE a.attraction_name = ?
                GROUP BY a.attraction_id, a.attraction_name,
                         a.description, a.city_name
                """;

        List<TouristAttraction> list =
                jdbcTemplate.query(sql, new AttractionRowMapper(), name);

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    // READ: finder attraktioner hvor beskrivelsen indeholder teksten
    public List<TouristAttraction> findTouristAttractionsByDescription(String description) {
        String sql = """
                SELECT a.attraction_id, a.attraction_name, a.description,
                       a.city_name, GROUP_CONCAT(t.tag_name) AS tags
                FROM attraction a
                LEFT JOIN attraction_tag at ON a.attraction_id = at.attraction_id
                LEFT JOIN tag t ON at.tag_id = t.tag_id
                WHERE a.description LIKE ?
                GROUP BY a.attraction_id, a.attraction_name,
                         a.description, a.city_name
                """;

        return jdbcTemplate.query(
                sql, new AttractionRowMapper(), "%" + description + "%"
        );
    }

    // UPDATE: opdaterer en attraktion ud fra navn
    public TouristAttraction updateTouristAttraction(String name, TouristAttraction updated) {
        String sql = """
                UPDATE attraction
                SET attraction_name = ?, description = ?, city_name = ?
                WHERE attraction_name = ?
                """;

        int rows = jdbcTemplate.update(
                sql, updated.getName(), updated.getDescription(),
                updated.getCity(), name
        );

        if (rows > 0) {
            updateTags(updated.getName(), updated.getTags());
            return updated;
        }

        return null;
    }

    // Hjælpemetode: opdaterer tags i junction-tabellen
    private void updateTags(String name, List<Tag> tags) {
        Integer attractionId = jdbcTemplate.queryForObject(
                "SELECT attraction_id FROM attraction WHERE attraction_name = ?",
                Integer.class,
                name
        );

        jdbcTemplate.update(
                "DELETE FROM attraction_tag WHERE attraction_id = ?",
                attractionId
        );

        for (Tag tag : tags) {
            Integer tagId = jdbcTemplate.queryForObject(
                    "SELECT tag_id FROM tag WHERE tag_name = ?",
                    Integer.class,
                    tag.name()
            );

            jdbcTemplate.update(
                    "INSERT INTO attraction_tag (attraction_id, tag_id) VALUES (?, ?)",
                    attractionId, tagId
            );
        }
    }

    // UPDATE: tilføjer et tag til en attraktion
    public TouristAttraction AddTagToTouristattraction(String name, Tag tag) {
        TouristAttraction a = findByName(name);

        if (a == null) {
            return null;
        }

        if (!a.getTags().contains(tag)) {
            a.addTag(tag);
            updateTags(a.getName(), a.getTags());
        }

        return a;
    }

    // DELETE: sletter en attraktion ud fra navn
    public boolean deleteTouristAttraction(String name) {
        Integer attractionId;

        try {
            attractionId = jdbcTemplate.queryForObject(
                    "SELECT attraction_id FROM attraction WHERE attraction_name = ?",
                    Integer.class,
                    name
            );
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return false;
        }

        jdbcTemplate.update(
                "DELETE FROM attraction_tag WHERE attraction_id = ?",
                attractionId
        );

        return jdbcTemplate.update(
                "DELETE FROM attraction WHERE attraction_id = ?",
                attractionId
        ) > 0;
    }

    // tags uden dubletter
    public List<String> getTags() {
        String sql = "SELECT tag_name FROM tag ORDER BY tag_name";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
