
package com.example.touristguide2;



import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.repository.TouristRepository;
import com.example.touristguide2.service.TouristService;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TouristServiceTest {

    @Test
    void testGetTouristAttractionByName() {

        //opretter en falsk repository,så opretter vi et service objekt
        TouristRepository repository = mock(TouristRepository.class);
        TouristService service = new TouristService(repository);

        TouristAttraction attraction = mock(TouristAttraction.class);

        // Bestemmer, hvad repository skal returnere , så Kalder metoden, der skal testes

        when(repository.findByName("Tivoli")).thenReturn(attraction);
        TouristAttraction result =
                service.getTouristAttractionByName("Tivoli");

        // Assert Tjekker, om resultatet er true-
        assertEquals(attraction, result);
        verify(repository).findByName("Tivoli");
    }
}
