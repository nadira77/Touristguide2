package com.example.touristguide2;

import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.repository.TouristRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(
        scripts = "classpath:h2init.sql",
        executionPhase = BEFORE_TEST_METHOD
)
class TouristGuide3RepositoryTest {

    @Autowired
    private TouristRepository repo;

    @Test
    void readAll() {
        List<TouristAttraction> all = repo.getTouristAttractions();

        assertThat(all).isNotNull();
        assertThat(all).hasSize(2);
        assertThat(all.get(0).getName()).isEqualTo("Tivoli");
        assertThat(all.get(1).getName()).isEqualTo("Rosenborg Castle");
    }
}
