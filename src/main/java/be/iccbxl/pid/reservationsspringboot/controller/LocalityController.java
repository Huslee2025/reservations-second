package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.reservationsspringboot.model.Locality;
import be.iccbxl.pid.reservationsspringboot.service.LocalityService;

@Controller
public class LocalityController {

	@Autowired
	private LocalityService localityService;

	@GetMapping("/localities")
	public String index(Model model) {
		List<Locality> localities = localityService.getAllLocalities();

		model.addAttribute("localities", localities);
		model.addAttribute("title", "Liste des localités");

		return "locality/index";
	}

	@GetMapping("/localities/{id}")
	public String show(Model model, @PathVariable("id") long id) {
		Locality locality = localityService.getLocality(id);

		model.addAttribute("locality", locality);
		model.addAttribute("title", "Fiche d'une localité");

		return "locality/show";
	}
}
