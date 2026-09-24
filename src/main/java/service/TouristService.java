package service;

import Model.Tag;
import Model.TouristAttraction;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import repository.TouristRepository;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAllTouristAttractions() {
        return touristRepository.getTouristAttractions();
    }

    public TouristAttraction addTag(String name, Tag tag){
        return touristRepository.AddTagToTouristattraction(name, tag);
    }


    public TouristAttraction getTouristAttractionByName(String name) {
        return touristRepository.findByName(name);
    }

    public List<TouristAttraction> getTouristAttractionsByDescription(String description) {
        return touristRepository.findTouristAttractionsByDescription(description);
    }


    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction) {
        return touristRepository.save(touristAttraction);
    }

    public TouristAttraction updateTouristAttraction(String name, TouristAttraction touristAttraction) {
        return touristRepository.updateTouristAttraction(name, touristAttraction);
    }

    public boolean deleteTouristAttraction(String name) {
        return touristRepository.deleteTouristAttraction(name);
    }

    public TouristAttraction getTouristAttraction(String name) {
        return touristRepository.findByName(name);
    }

    public List<String> getCities() {
        return touristRepository.getCities();
    }

    public List<String> getTags() {
        return touristRepository.getTags();
    }



}

