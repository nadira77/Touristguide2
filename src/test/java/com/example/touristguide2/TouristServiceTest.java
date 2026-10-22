
package com.example.touristguide2;


import Model.TouristAttraction;
import org.junit.jupiter.api.Test;
import repository.TouristRepository;
import service.TouristService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TouristServiceTest {

    @Test
    void testGetTouristAttractionByName() {

        //opretter en falsk repository, opretter et service objekt
        TouristRepository repository = mock(TouristRepository.class);
        TouristService service = new TouristService(repository);

        TouristAttraction attraction = mock(TouristAttraction.class);

        // Bestemmer, hvad repository skal returnere , så Kalder metoden, der skal testes

        when(repository.findByName("Tivoli")).thenReturn(attraction);
        TouristAttraction result =
                service.getTouristAttractionByName("Tivoli");

        // Assert Tjekker, om resultatet er true
        assertEquals(attraction, result);
        verify(repository).findByName("Tivoli");
    }
}
