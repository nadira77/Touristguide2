package service;

import Model.TouristAttraction;
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
}