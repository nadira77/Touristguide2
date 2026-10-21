package Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttractionRowMapper implements RowMapper<TouristAttraction> {
    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
        // henter kolonerne fra JOIN
        String name = rs.getString("attraction_name");
        String description = rs.getString("description");
        String city = rs.getString("city_name");

        //Tags hentes fra repository, fordi de ligger i en anden tabel
        return new TouristAttraction(name, description, city, new ArrayList<>());
    }


}


