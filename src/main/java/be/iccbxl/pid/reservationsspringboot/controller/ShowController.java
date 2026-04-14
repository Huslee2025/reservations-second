package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.ArtistType;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;

@Controller
public class ShowController {

    private final ShowService service;

    public ShowController(ShowService service) {
	this.service = service;
    }

    @GetMapping("/shows")
    public String index(Model model) {
	List<Show> shows = service.getAll();

	model.addAttribute("shows", shows);
	model.addAttribute("title", "Liste des spectacles");
	model.addAttribute("module", "shows");

	return "show/index";
    }

    @GetMapping("/shows/{id}")
    public String show(Model model, @PathVariable("id") Long id) {
	Show show = service.get(id);

	if (show == null) {
	    return "redirect:/shows";
	}

	// Récupérer les artistes du spectacle et les grouper par type
	Map<String, ArrayList<Artist>> collaborateurs = new TreeMap<>();

	for (ArtistType at : show.getArtistTypes()) {
	    String type = at.getType().getType();

	    if (collaborateurs.get(type) == null) {
		collaborateurs.put(type, new ArrayList<>());
	    }

	    collaborateurs.get(type).add(at.getArtist());
	}

	model.addAttribute("collaborateurs", collaborateurs);
	model.addAttribute("show", show);
	model.addAttribute("title", "Fiche d'un spectacle");
	model.addAttribute("module", "shows");

	return "show/show";
    }
}
