package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.service.ArtistService;

@Controller
public class ArtistController {
	@Autowired
	ArtistService service;

	//…

	@GetMapping("/artists")
	public String index(Model model) {
	    // Récupérer tous les artistes depuis la base
	    List<Artist> artists = service.getAllArtists();

	    // Ajouter les données au modèle (pour Thymeleaf)
	    model.addAttribute("artists", artists);
	    model.addAttribute("title", "Liste des artistes");

	    // Retourner la page index.html (dans templates/artist)
	    return "artist/index";
	}
	
	@GetMapping("/artists/create")
	public String create(Model model) {
	    Artist artist = new Artist();

	    model.addAttribute("artist", artist);
		
	    return "artist/create";
	}
	
	@PostMapping("/artists/create")
	public String store(@Valid @ModelAttribute Artist artist, BindingResult bindingResult, Model model) {
	    
	    if (bindingResult.hasErrors()) {
		return "artist/create";
	    }
		    
	    service.addArtist(artist);
	    
	    return "redirect:/artists/"+artist.getId();
	}

}
