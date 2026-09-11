package Controller;

import Model.TouristAttraction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.TouristService;

@Controller
@RequestMapping("attractions")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) { // constructor injection
        this.touristService = touristService;
    }

    @GetMapping({"/", "/index.html"})
    public String index() {
        return "index";
    }

    @GetMapping
    public String getAllAttractions(Model model) {
        model.addAttribute("attractions", touristService.getAllTouristAttractions());
        return "attractionList";
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getByName(@PathVariable String name) { // GET /attractions/{name}
        TouristAttraction attraction = touristService.getTouristAttractionByName(name);
        if (attraction == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(attraction);
    }

    @PostMapping("/add")
    public ResponseEntity<TouristAttraction> add(@RequestBody TouristAttraction touristAttraction) { // POST /attractions/add
        return ResponseEntity.ok(touristService.addTouristAttraction(touristAttraction));
    }

    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> update(@RequestBody TouristAttraction touristAttraction) { // POST /attractions/update
        TouristAttraction updated = touristService.updateTouristAttraction(touristAttraction.getName(), touristAttraction);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<String> delete(@PathVariable String name) { // POST /attractions/delete/{name}
        boolean deleted = touristService.deleteTouristAttraction(name);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Attraktion slettet");
    }
}
