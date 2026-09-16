package Controller;

import Model.TouristAttraction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.TouristService;
import Model.Tag;

@Controller
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) { // constructor injection
        this.touristService = touristService;
    }

    @GetMapping({"/", "/index.html"})
    public String welcome() {
        return "index";
    }

    @GetMapping("/copenhagen")
    public String copenhagen() {
        return "copenhagen";
    }

    @GetMapping("/attractions")
    public String getAllAttractions(Model model) {
        model.addAttribute("touristAttractions", touristService.getAllTouristAttractions());
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String getTags(Model model, @PathVariable String name) {
        model.addAttribute("attractions",touristService.getTouristAttractionByName(name));
        return "tags";
    }

  /*  @GetMapping("/attractions/add")
    public String addForm( Model model) {
        model.addAttribute("attraction", new TouristAttraction());
        return "add";
    }*/
    //Tag.values() gives Thymeleaf adgang til enum og husk import enum class
  @GetMapping("/attractions/add")
  public String addForm(Model model) {
      model.addAttribute("attraction", new TouristAttraction());
      model.addAttribute("tags", Tag.values());
      return "add";
  }

    @PostMapping("/save")
    public String save(@ModelAttribute TouristAttraction attraction) {
        touristService.addTouristAttraction(attraction); // gem det nye objekt i repository og efter send bruger tilbage til listen
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getTouristAttraction(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("allCities", touristService.getCities());
        model.addAttribute("allTags", touristService.getTags());
        return "updateAttraction";
    }

    @PostMapping("/attractions/update")
    public String update(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateTouristAttraction(touristAttraction.getName(), touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/attractions/delete/{name}")
    public String delete(@PathVariable String name) {
        touristService.deleteTouristAttraction(name);
        return "redirect:/attractions";
    }
}
