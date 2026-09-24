package com.example.touristguide2;
import Controller.TouristController;
import Model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import service.TouristService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

@WebMvcTest(TouristController.class)
public class TouristControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TouristService touristService;

    @Test
    void saveAttraction_redirectsToAttractions() throws Exception {
        mockMvc.perform(post("/save")
                        .param("name", "SMK")
                        .param("description", "Museum for Kunst")
                        .param("city", "København"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        verify(touristService).addTouristAttraction(any(TouristAttraction.class));
    }
    @Test
    void delete() throws Exception{
        mockMvc.perform(post("/attractions/delete/{name}", "Tivoli"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        verify(touristService).deleteTouristAttraction("Tivoli");

    }
    @Test
    void delete() throws Exception{
        mockMvc.perform(post("/attractions/delete/{name}", "Tivoli"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        verify(touristService).deleteTouristAttraction("Tivoli");

    }

    @Test
    void shouldUpdateAttraction() throws Exception {
        mockMvc.perform(post("/attractions/update")
                        .param("name", "Tivoli")
                        .param("description", "Updated description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attractions"));

        verify(touristService).updateTouristAttraction(eq("Tivoli"), any(TouristAttraction.class));
    }
}
