
package Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttractionRowMapper implements RowMapper<TouristAttraction> {

    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString("attraction_name");
        String description = rs.getString("description");
        String city = rs.getString("city_name");

        String tagString = rs.getString("tags");
        List<Tag> tags = new ArrayList<>();

        if (tagString != null && !tagString.isEmpty()) {
            Arrays.stream(tagString.split(","))
                    .forEach(tag -> tags.add(Tag.valueOf(tag)));
        }

        return new TouristAttraction(name, description, city, tags);
    }
}
